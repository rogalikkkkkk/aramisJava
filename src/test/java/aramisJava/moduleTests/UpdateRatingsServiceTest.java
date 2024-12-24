package aramisJava.moduleTests;

import org.example.*;
import org.example.service.UpdateRatingsService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateRatingsServiceTest {

    private final UpdateRatingsService service = new UpdateRatingsService();

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testMapOldRatingsToNew(
            List<Criterion> criteria,
            List<String> oldRatings,
            List<String> expectedRatings
    ) {
        // Создаем Problem и RatingWithProblem
        Problem problem = new Problem("Test Problem", criteria);
        Rating oldRating = new Rating("Test Problem", "Alternative 1", oldRatings);
        RatingWithProblem ratingWithProblem = new RatingWithProblem(oldRating, problem);

        // Вызываем тестируемый метод
        List<RatingWithProblem> newRatings = service.mapOldRatingsToNew(List.of(ratingWithProblem));

        // Проверяем, что новый рейтинг соответствует ожиданиям
        List<String> actualRatings = newRatings.getFirst().rating().getAlternativeRatings();
        assertEquals(expectedRatings, actualRatings);
    }

    // Метод для предоставления тестовых данных
    private static Stream<org.junit.jupiter.params.provider.Arguments> provideTestCases() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(
                                new Criterion("Criterion 1", Scale.LONG, OptimizationDirection.MAX),
                                new Criterion("Criterion 2", Scale.BASE, OptimizationDirection.MIN)
                        ),
                        List.of("ЭВ", "С"),
                        List.of("ОВ", "С")
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(
                                new Criterion("Criterion 1", Scale.BASE, OptimizationDirection.MAX),
                                new Criterion("Criterion 2", Scale.SHORT, OptimizationDirection.MIN)
                        ),
                        List.of("Н", "В"),
                        List.of("Н", "ОН")
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(
                                new Criterion("Criterion 1", Scale.SHORT, OptimizationDirection.MAX),
                                new Criterion("Criterion 2", Scale.LONG, OptimizationDirection.MIN)
                        ),
                        List.of("В", "ЭН"),
                        List.of("ОВ", "ОВ")
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(
                                new Criterion("Criterion 1", Scale.SHORT, OptimizationDirection.MAX),
                                new Criterion("Criterion 2", Scale.LONG, OptimizationDirection.MIN),
                                new Criterion("Criterion 3", Scale.BASE, OptimizationDirection.MIN)
                        ),
                        List.of("В", "ЭН", "ОН"),
                        List.of("ОВ", "ОВ", "ОВ")
                )
        );
    }
}
