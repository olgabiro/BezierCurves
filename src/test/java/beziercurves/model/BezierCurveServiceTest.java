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
    private final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor = mock(BezierCurveDecreaseDegreeProcessor.class);
    private final BezierCurveHermiteProcessor hermiteProcessor = mock(BezierCurveHermiteProcessor.class);

    private final BezierCurveService service = new BezierCurveService(this.increaseDegreeProcessor,
                                                                      this.decreaseDegreeProcessor,
                                                                      this.hermiteProcessor);

    @Test
    void constructor_throwsIllegalArgumentException_whenIncreaseDegreeProcessorIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierCurveService(null,
                                                  this.decreaseDegreeProcessor,
                                                  this.hermiteProcessor));
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenDecreaseDegreeProcessorIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierCurveService(this.increaseDegreeProcessor,
                                                  null,
                                                  this.hermiteProcessor));
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenHermiteProcessorIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierCurveService(this.increaseDegreeProcessor,
                                                  this.decreaseDegreeProcessor,
                                                  null));
    }

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

    @Test
    void decreaseDegree_throwsIllegalArgumentException_whenCurveIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> this.service.decreaseDegree(null));
    }

    @Test
    void decreaseDegree_delegatesToDecreaseDegreeProcessor() {
        final BezierCurve bezierCurve = this.bezierCurveTestFixture.create();

        this.service.decreaseDegree(bezierCurve);

        verify(this.decreaseDegreeProcessor,
               times(1)).decreaseDegree(bezierCurve);
        verifyNoMoreInteractions(this.decreaseDegreeProcessor);
    }

    @Test
    void decreaseDegreeHermite_throwsIllegalArgumentException_whenCurveIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> this.service.decreaseDegreeHermite(null));
    }

    @Test
    void decreaseDegreeHermite_delegatesToDecreaseDegreeProcessor() {
        final BezierCurve bezierCurve = this.bezierCurveTestFixture.create();

        this.service.decreaseDegreeHermite(bezierCurve);

        verify(this.hermiteProcessor,
               times(1)).decreaseDegree(bezierCurve);
        verifyNoMoreInteractions(this.hermiteProcessor);
    }
}