package challenge;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ReactiveExample {

    public static final int VALOR_PERMITIDO = 15;
    private final Flux<Estudiante> estudianteList;

    public ReactiveExample() {
        this.estudianteList = Flux.just(
                new Estudiante("raul", 30, List.of(1, 2, 1, 4, 5)),
                new Estudiante("andres", 35, List.of(4, 2, 4, 3, 5)),
                new Estudiante("juan", 75, List.of(3, 2, 4, 5, 5)),
                new Estudiante("pedro", 80, List.of(5, 5, 4, 5, 5)),
                new Estudiante("santiago", 40, List.of(4, 5, 4, 5, 5))
        );
    }

    public Mono<Integer> sumaDePuntajes() {
        return estudianteList.map(mapeoDeEstudianteAPuntaje())
                .reduce(0, Integer::sum);
    }

    private Function<Estudiante, Integer> mapeoDeEstudianteAPuntaje() {
        return Estudiante::getPuntaje;
    }

    public Mono<Estudiante> mayorPuntajeDeEstudiante() {
        return estudianteList.sort(Comparator.reverseOrder())
                .elementAt(0);
    }

    public Mono<Integer> totalDeAsisntenciasDeEstudiantesConMayorPuntajeDe(int valor) {
        return estudianteList.filter(estudianteConPuntajeMayorDe(valor))
                .flatMapIterable(Estudiante::getAsistencias)
                .reduce(Integer::sum);
    }

    private Predicate<Estudiante> estudianteConPuntajeMayorDe(int valor) {
        return estudiante -> estudiante.getPuntaje() >= valor;
    }

    public Mono<Boolean> elEstudianteTieneAsistenciasCorrectas(Estudiante estudiante) {
        return Mono.just(estudiante)
                .filter(estudianteConAsistenciasMayorAPermitidas())
                .hasElement();
    }

    private Predicate<Estudiante> estudianteConAsistenciasMayorAPermitidas() {
        return estudiante -> estudiante.getAsistencias()
                .stream()
                .reduce(0, Integer::sum) >= VALOR_PERMITIDO;
    }

    public Mono<Double> promedioDePuntajesPorEstudiantes() {
        return estudianteList.map(Estudiante::getPuntaje)
                .collect(Collectors.averagingDouble(puntaje -> puntaje));
    }

    public Flux<String> losNombresDeEstudianteConPuntajeMayorA(int valor) {
        return estudianteList.filter(estudianteConPuntajeMayorDe(valor))
                .map(Estudiante::getNombre);
    }

    public Flux<String> estudiantesAprovados(){
        return estudianteList.map(this::aprobarEstudiante)
                .filter(Estudiante::isAprobado)
                .map(Estudiante::getNombre);
    }

    private Estudiante aprobarEstudiante(Estudiante estudiante) {
        return Optional.of(estudiante)
                .filter(estudianteConPuntajeMayorDe(75))
                .map(this::aprobar)
                .orElse(estudiante);
    }

    public Estudiante aprobar(Estudiante estudiante) {
        var est = new Estudiante(estudiante.getNombre(), estudiante.getPuntaje(), estudiante.getAsistencias());
        est.setAprobado(true);

        return est;
    }
}
