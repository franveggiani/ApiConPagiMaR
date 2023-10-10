package com.example.ApiRestConPaginacion.repository;
import com.example.ApiRestConPaginacion.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long>{

    //Anotacion Metodo @Query

    List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido);

    // Anotacion JPQL metodo parametros indexados
    @Query(
            value = "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%"
    )

    List<Persona> search(@Param("filtro") String filtro);

    //Anotacion @QueryNativa metodo parametros nombrados
    @Query(
            value = "SELECT * FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%" ,
            nativeQuery = true
    )

    List<Persona> searchNative(@Param("filtro") String filtro);


}
