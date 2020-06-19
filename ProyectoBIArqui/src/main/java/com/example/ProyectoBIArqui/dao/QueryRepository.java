package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Persona1;
import com.example.ProyectoBIArqui.domain.Querybi;
import com.example.ProyectoBIArqui.dto.QueryXY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<Querybi,Integer> {
    List<Querybi> findAll();
    Querybi findQuerybiByQuery(String query);
    Querybi findQuerybiByIdQuerybi(int pk);
    @Query(value = "SELECT a.fecha from persona_1 a, estado b where a.id_estado = b.id_estado and b.id_estado=1 group by a.fecha", nativeQuery = true)
    List<String> numeroAcumuladoDeContagiadosPorFecha1();
    @Query(value = "SELECT count(a.fecha) from persona_1 a, estado b where a.id_estado = b.id_estado and b.id_estado=1 group by a.fecha", nativeQuery = true)
    List<Integer> numeroAcumuladoDeContagiadosPorFecha2();
    @Query(value = "select d.pais from persona_1 a, estado b , origen c, pais d  where a.id_estado = b.id_estado and b.id_estado=1 and c.id_pais = d.id_pais and c.id_origen = a.id_origen group by d.pais", nativeQuery = true)
    List<String> contagiadosPorPais1();

    @Query(value = "select count (a.id_persona) from persona_1 a, estado b , origen c, pais d  where a.id_estado = b.id_estado and b.id_estado=1 and c.id_pais = d.id_pais and c.id_origen = a.id_origen group by d.pais", nativeQuery = true)
    List<Integer> contagiadosPorPais2();

    @Query(value = "select c.edad from persona_1 a, estado b, edad c where a.id_estado = b.id_estado and b.id_estado=1 and a.id_edad = c.id_edad group by c.edad", nativeQuery = true)
    List<String> contagiadosPorEdad1();

    @Query(value = "select count(a.id_persona) from persona_1 a, estado b, edad c where a.id_estado = b.id_estado and b.id_estado=1 and a.id_edad = c.id_edad group by c.edad", nativeQuery = true)
    List<Integer> contagiadosPorEdad2();

    @Query(value = "select c.departamento from persona_1 a, estado b, departamento c,residencia_1 d where a.id_estado = b.id_estado and b.id_estado=1 and a.id_residencia=d.id_residencia and d.id_departamento = c.id_departamento group by c.departamento", nativeQuery = true)
    List<String> contagiadosPorDepartamento1();

    @Query(value = "select count(a.id_persona) from persona_1 a, estado b, departamento c,residencia_1 d where a.id_estado = b.id_estado and b.id_estado=1 and a.id_residencia=d.id_residencia and d.id_departamento = c.id_departamento group by c.departamento", nativeQuery = true)
    List<Integer> contagiadosPorDepartamento2();

    @Query(value = "select c.sexo from persona_1 a, estado b, sexo c where a.id_estado = b.id_estado and b.id_estado=1 and a.id_sexo = c.id_sexo group by c.sexo", nativeQuery = true)
    List<String> contagiadosPorSexo1();

    @Query(value = "select count(a.id_persona) from persona_1 a, estado b, sexo c where a.id_estado = b.id_estado and b.id_estado=1 and a.id_sexo = c.id_sexo group by c.sexo", nativeQuery = true)
    List<Integer> contagiadosPorSexo2();

    @Query(value = "select a.fecha from persona_1 a, estado b where a.id_estado = b.id_estado and b.id_estado=2 group by a.fecha", nativeQuery = true)
    List<String> recuperadosPorFecha1();

    @Query(value = "select count(a.fecha) from persona_1 a, estado b where a.id_estado = b.id_estado and b.id_estado=2 group by a.fecha", nativeQuery = true)
    List<Integer> recuperadosPorFecha2();

    @Query(value = "select d.pais from persona_1 a, estado b , origen c, pais d where a.id_estado = b.id_estado and b.id_estado=2 and c.id_pais = d.id_pais and c.id_origen = a.id_origen group by d.pais", nativeQuery = true)
    List<String> recuperadosPorPais1();

    @Query(value = "select count (a.id_persona) from persona_1 a, estado b , origen c, pais d where a.id_estado = b.id_estado and b.id_estado=2 and c.id_pais = d.id_pais and c.id_origen = a.id_origen group by d.pais", nativeQuery = true)
    List<Integer> recuperadosPorPais2();

    @Query(value = "select c.edad from persona_1 a, estado b, edad c where a.id_estado = b.id_estado and b.id_estado=2 and a.id_edad = c.id_edad group by c.edad", nativeQuery = true)
    List<String> recuperadosPorEdad1();

    @Query(value = "select count(a.id_persona) from persona_1 a, estado b, edad c where a.id_estado = b.id_estado and b.id_estado=2 and a.id_edad = c.id_edad group by c.edad", nativeQuery = true)
    List<Integer> recuperadosPorEdad2();

    @Query(value = "select c.sexo from persona_1 a, estado b, sexo c where a.id_estado = b.id_estado and b.id_estado=2 and a.id_sexo = c.id_sexo group by c.sexo", nativeQuery = true)
    List<String> recuperadosPorSexo1();

    @Query(value = "select count(a.id_persona) from persona_1 a, estado b, sexo c where a.id_estado = b.id_estado and b.id_estado=2 and a.id_sexo = c.id_sexo group by c.sexo", nativeQuery = true)
    List<Integer> recuperadosPorSexo2();

    @Query(value = "select a.fecha from persona_1 a, estado b where a.id_estado = b.id_estado and b.id_estado=3 group by a.fecha", nativeQuery = true)
    List<String> muertosPorFecha1();

    @Query(value = "select count(a.fecha) from persona_1 a, estado b where a.id_estado = b.id_estado and b.id_estado=3 group by a.fecha", nativeQuery = true)
    List<Integer> muertosPorFecha2();

    @Query(value = "select d.pais from persona_1 a, estado b , origen c, pais d where a.id_estado = b.id_estado and b.id_estado=3 and c.id_pais = d.id_pais and c.id_origen = a.id_origen group by d.pais", nativeQuery = true)
    List<String> muertosPorPais1();

    @Query(value = "select  count (a.id_persona) from persona_1 a, estado b , origen c, pais d where a.id_estado = b.id_estado and b.id_estado=3 and c.id_pais = d.id_pais and c.id_origen = a.id_origen group by d.pais", nativeQuery = true)
    List<Integer> muertosPorPais2();

    @Query(value = "select c.edad from persona_1 a, estado b, edad c where a.id_estado = b.id_estado and b.id_estado=3 and a.id_edad = c.id_edad group by c.edad ", nativeQuery = true)
    List<String> muertosPorEdad1();

    @Query(value = "select count(a.id_persona) from persona_1 a, estado b, edad c where a.id_estado = b.id_estado and b.id_estado=3 and a.id_edad = c.id_edad group by c.edad ", nativeQuery = true)
    List<Integer> muertosPorEdad2();

    @Query(value = " select c.sexo from persona_1 a, estado b, sexo c where a.id_estado = b.id_estado and b.id_estado=3 and a.id_sexo = c.id_sexo group by c.sexo", nativeQuery = true)
    List<String> muertosPorSexo1();

    @Query(value = " select count(a.id_persona) from persona_1 a, estado b, sexo c where a.id_estado = b.id_estado and b.id_estado=3 and a.id_sexo = c.id_sexo group by c.sexo", nativeQuery = true)
    List<Integer> muertosPorSexo2();
}
