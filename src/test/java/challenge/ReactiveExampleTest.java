package challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


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

    }

    @Test
    void promedioDePuntajesPorEstudiantes(){

    }

    @Test
    void estudiantesAprovados(){

    }
}