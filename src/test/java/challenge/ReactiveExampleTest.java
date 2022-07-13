package challenge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class ReactiveExampleTest {

    @InjectMocks
    ReactiveExample reactiveExample;


    @Test
    void sumaDePuntajes(){
        Mono<Integer> valor = reactiveExample.sumaDePuntajes();

        StepVerifier.create(valor)
                .expectNext(260)
                .verifyComplete();
    }

    @Test
    void mayorPuntajeDeEstudiante(){
        var estudiante = reactiveExample.mayorPuntajeDeEstudiante();

        StepVerifier.create(estudiante)
                .expectNextMatches(response -> response.getPuntaje().equals(80)
                                                                && response.getNombre().equals("pedro"))
                .verifyComplete();
    }

    @Test
    void totalDeAsisntenciasDeEstudiantesComMayorPuntajeDe(){
        var puntaje = 75;
        var totalAsistencias = reactiveExample.totalDeAsisntenciasDeEstudiantesConMayorPuntajeDe(puntaje);

        StepVerifier.create(totalAsistencias)
                .expectNext(43)
                .verifyComplete();
    }

    @Test
    void elEstudianteTieneAsistenciasCorrectas(){
        var estudiante = new Estudiante("raul", 30, List.of(5,2,1,4,5));
        var tieneAsistencias = reactiveExample.elEstudianteTieneAsistenciasCorrectas(estudiante);

        StepVerifier.create(tieneAsistencias)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void promedioDePuntajesPorEstudiantes(){
        var promedioPuntajes = reactiveExample.promedioDePuntajesPorEstudiantes();

        StepVerifier.create(promedioPuntajes)
                .expectNext(52.0)
                .verifyComplete();
    }

    @Test
    void losNombresDeEstudianteConPuntajeMayorA() {
        var puntaje = 50;

        var estudiantes = reactiveExample.losNombresDeEstudianteConPuntajeMayorA(puntaje);

        StepVerifier.create(estudiantes)
                .expectNext("juan", "pedro")
                .verifyComplete();
    }

    @Test
    void estudiantesAprovados(){
        var estudiantes = reactiveExample.estudiantesAprovados();

        StepVerifier.create(estudiantes)
                .expectNext("juan", "pedro")
                .verifyComplete();
    }
}