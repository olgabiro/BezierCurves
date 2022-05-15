package beziercurves.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.Test;

class BezierCurveServiceTest {

    private final BezierCurveTestFixture bezierCurveTestFixture = new BezierCurveTestFixture();

    private final BezierCurveIncreaseDegreeProcessor increaseDegreeProcessor = mock(BezierCurveIncreaseDegreeProcessor.class);
    private final BezierCurveService service = new BezierCurveService(this.increaseDegreeProcessor);

    @Test
    void increaseDegree_throwsIllegalArgumentException_whenCurveIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> this.service.increaseDegree(null));
    }

    @Test
    void increaseDegree_delegatesToIncreaseDegreeProcessor() {
        final BezierCurve bezierCurve = this.bezierCurveTestFixture.create();

        this.service.increaseDegree(bezierCurve);

        verify(this.increaseDegreeProcessor,
               times(1)).increaseDegree(bezierCurve);
        verifyNoMoreInteractions(this.increaseDegreeProcessor);
    }
}