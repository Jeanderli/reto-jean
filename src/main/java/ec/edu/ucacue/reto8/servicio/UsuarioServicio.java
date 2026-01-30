/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.dao.UsuarioDao;
import ec.edu.ucacue.reto8.domain.Rol;
import ec.edu.ucacue.reto8.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author L 303 PC3
 */
@Service("userDetailsService")
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);        

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        var roles = new ArrayList<GrantedAuthority>();
        
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
    @Transactional
    public void registrarUsuario(Usuario usuario) {
        // Encriptar contraseña
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));

        // Agregar rol por defecto si no se asignó ninguno
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            Rol rolUser = new Rol();
            rolUser.setNombre("ROLE_USER");
            usuario.setRoles(List.of(rolUser));
        }

    usuarioDao.save(usuario);
    }
}