package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.DocumentType;
import com.jfarro.app.models.domains.Role;
import com.jfarro.app.models.domains.User;
import com.jfarro.app.services.FileDirectoryService;
import com.jfarro.app.services.UserService;
import com.jfarro.app.utils.PathDirectoryEnums;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemUserController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("user")
    private Validator validator;

    @Autowired
    private FileDirectoryService fileDirectoryService;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
        webDataBinder.registerCustomEditor(String.class, "names", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apePat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apeMat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "email", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "observation", new StringUppercaseEditor());
    }

    @GetMapping("/control/usuarios")
    public String showSystemUsers(User user, Model model, HttpServletRequest request) {
        dataShowUser(model, request);
        return "sistema/users";
    }

    @PostMapping("/control/usuarios")
    public String saveUser(@Valid User user, BindingResult bindingResult,
                           HttpServletRequest request, Model model,
                           @RequestParam("file") MultipartFile file, RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            dataShowUser(model, request);
            model.addAttribute("errors", true);
            return "sistema/users";
        }

        //Obteniendo datos de la sesion al buscar por id
        if (user.getId() != null && user.getId() > 0) {
            User userSession = (User) request.getSession().getAttribute("user");

            user.getUserHistory().setState(userSession.getUserHistory().getState());
            user.getUserHistory().setUserReg(userSession.getUserHistory().getUserReg());
            user.getUserHistory().setDateReg(userSession.getUserHistory().getDateReg());
            user.getUserHistory().setUserMod(userSession.getUserHistory().getUserMod());
            user.getUserHistory().setDateMod(userSession.getUserHistory().getDateMod());

            if (userSession.getPhoto() != null && userSession.getPhoto().length() > 0) {
                user.setPhoto(userSession.getPhoto());
            }
        }
        request.getSession().removeAttribute("user");

        if (!file.isEmpty()) {
            //En el caso que la foto del usuario exista junto con su id, se reemplazara la foto eliminandolo al anterior
            if (user.getId() != null && user.getId() > 0
                    && user.getPhoto() != null && user.getPhoto().length() > 0) {
                fileDirectoryService.deleteFile(user.getPhoto(), PathDirectoryEnums.USER_FILE.directorys);
            }

            //Guarda la foto en el directorio establecido y el nombre en l base de datos
            try {
                String filename = fileDirectoryService.copyFile(file, PathDirectoryEnums.USER_FILE.directorys);
                user.setPhoto(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (user.getId() != null && user.getId() > 0) {
            flash.addFlashAttribute("success", "Usuario editado exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Usuario registrado exitosamente.");
        }
        userService.saveUser(user);
        return "redirect:/system-sport-shop/control/usuarios";
    }

    @GetMapping("/control/usuario-delete")
    public String updateState(@RequestParam("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Optional<User> userOptional = userService.findByIdUser(id);
            if (userOptional.isPresent()) {
                Byte state = 0;
                userService.updateStateUser(state, id);
                flash.addFlashAttribute("success", "Usuario eliminado exitosamente.");
            } else {
                flash.addFlashAttribute("error", "El usuario a eliminar ya no existe en la base de datos.");
            }
        } else {
            flash.addFlashAttribute("error", "El ID de usuario no puede ser menor o igual a cero.");
        }
        return "redirect:/system-sport-shop/control/usuarios";
    }

    @GetMapping({"/control/usuario-edit", "/control/user-data"})
    @ResponseBody
    public User selectIdUser(@RequestParam("id") Long id, HttpServletRequest request) { //Cuando se edita hay que guardarlo en la sesion para matener el id del usuario seleccionado
        User user = null;
        if (id > 0) {
            Optional<User> userOptional = userService.findByIdUser(id);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                request.getSession().setAttribute("user", user);
            }
        }
        return user;
    }

    @GetMapping("/photousers/{filename:.+}")
    public ResponseEntity<Resource> showDataPhoto(@PathVariable("filename") String filename) {
        Resource resource = null;
        try {
            resource = fileDirectoryService.loadFile(filename, PathDirectoryEnums.USER_FILE.directorys);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(resource.getFilename()).concat("\""))
                .body(resource);
    }

    private void dataShowUser(Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("user");

        model.addAttribute("usersActive", true); //Para activar la seleccion de las paginas en el menu

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);

        List<Role> roles = userService.findAllRoles();
        model.addAttribute("roles", roles);

        List<DocumentType> documentTypes = userService.findAllDocumentTypes();
        model.addAttribute("documentTypes", documentTypes);
    }
}
