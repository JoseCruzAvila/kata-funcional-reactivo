package challenge;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class ReactiveExample {

    public static final int VALOR_PERMITIDO = 15;
    private  Flux<Estudiante> estudianteList;

    public ReactiveExample() {
        this.estudianteList = Flux.fromIterable(List.of(
                new Estudiante("raul", 30, List.of(1, 2, 1, 4, 5)),
                new Estudiante("andres", 35, List.of(4, 2, 4, 3, 5)),
                new Estudiante("juan", 75, List.of(3, 2, 4, 5, 5)),
                new Estudiante("pedro", 80, List.of(5, 5, 4, 5, 5)),
                new Estudiante("santiago", 40, List.of(4, 5, 4, 5, 5))
        ));
    }

    public Mono<Integer> sumaDePuntajes() {
        return estudianteList.map(mapeoDeEstudianteAPuntaje())
                .reduce(0, Integer::sum);
    }

    private Function<Estudiante, Integer> mapeoDeEstudianteAPuntaje() {
        return Estudiante::getPuntaje;
    }

    public Mono<Estudiante> mayorPuntajeDeEstudiante() {
        return estudianteList.reduce((estudiante1, estudiante2) ->
                estudiante1.getPuntaje() > estudiante2.getPuntaje() ? estudiante1 : estudiante2);
    }

    public Mono<Integer> totalDeAsisntenciasDeEstudiantesConMayorPuntajeDe(int valor) {
        return estudianteList.filter(estudianteConPuntajeMayorDe(valor))
                .flatMapIterable(Estudiante::getAsistencias)
                .reduce(Integer::sum);
    }

    public Predicate<Estudiante> estudianteConPuntajeMayorDe(int valor) {
        return estudiante -> estudiante.getPuntaje() >= valor;
    }


    //TODO: el estudiante tiene asistencias correctas
    public Mono<Boolean> elEstudianteTieneAsistenciasCorrectas(Estudiante estudiante) {
        return null;
    }

    //TODO: promedio de puntajes por estudiantes
    public Mono<Double> promedioDePuntajesPorEstudiantes() {
        return null;
    }


    //TODO: los nombres de estudiante con puntaje mayor a un valor
    public Flux<String> losNombresDeEstudianteConPuntajeMayorA(int valor) {
        return null;
    }



    //TODO: estudiantes aprovados
    public Flux<String> estudiantesAprovados(){
        return null;
    }


}
