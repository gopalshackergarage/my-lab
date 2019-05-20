package railwayoriented;

import org.junit.jupiter.api.Test;
import railwayoriented.Egg.Yellow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static railwayoriented.ValidationFailure.VALIDATION_FAILURE_1;
import static railwayoriented.ValidationFailure.VALIDATION_FAILURE_2;
import static railwayoriented.ValidationFailure.VALIDATION_FAILURE_32;

public class ImperativeEggValidation {
    @Test
    void cyclomaticCode() {
        var eggList = Egg.getEggCarton();

        Map<Integer, ValidationFailure> badEggFailureBucketMap = new HashMap<>();
        var eggIndex = 0;
        for (var iterator = eggList.iterator(); iterator.hasNext(); eggIndex++) {
            var eggTobeValidated = iterator.next();
            if (!isValid1(eggTobeValidated)) {
                iterator.remove(); // Mutation
                // How do you cleanly map validation-failure to which validation-method failed?
                badEggFailureBucketMap.put(eggIndex, VALIDATION_FAILURE_1);
                continue;
            }
            try {
                if (!isValid2(eggTobeValidated)) {
                    iterator.remove();
                    badEggFailureBucketMap.put(eggIndex, VALIDATION_FAILURE_2);
                }
            } catch (Exception e) { // Repetition of same logic for exception handling
                iterator.remove();
                badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
                continue;
            }
            try { // Inter-dependent validations
                if (isValid31(eggTobeValidated)) {
                    var yellowTobeValidated = eggTobeValidated.getYellow();
                    if (yellowTobeValidated != null) { // Nested-if for null checking nested objects
                        try {
                            if (!isValid32(yellowTobeValidated)) {
                                iterator.remove();
                                badEggFailureBucketMap.put(eggIndex, VALIDATION_FAILURE_32);
                            }
                        } catch (Exception e) {
                            iterator.remove();
                            badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
                            continue;
                        }
                    }
                } else {
                    iterator.remove();
                    badEggFailureBucketMap.put(eggIndex, VALIDATION_FAILURE_2);
                }
            } catch (Exception e) {
                iterator.remove();
                badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
                continue;
            }
        }
    }

    @Test
    void cyclomaticCodeBrokenIntoFunctions() {
        var eggList = Egg.getEggCarton();

        Map<Integer, ValidationFailure> badEggFailureBucketMap = new HashMap<>();
        var eggIndex = 0;
        for (var iterator = eggList.iterator(); iterator.hasNext(); eggIndex++) {
            var eggTobeValidated = iterator.next();
            if (validate1(badEggFailureBucketMap, eggIndex, iterator, eggTobeValidated)) continue;

            if (validate2(badEggFailureBucketMap, eggIndex, iterator, eggTobeValidated)) continue;

            validate3(badEggFailureBucketMap, eggIndex, iterator, eggTobeValidated);
        }
    }

    private void validate3(Map<Integer, ValidationFailure> badEggFailureBucketMap, int eggIndex, Iterator<Egg> iterator, Egg eggTobeValidated) {
        try { // Inter-dependent validations
            if (isValid31(eggTobeValidated)) {
                var yellowTobeValidated = eggTobeValidated.getYellow();
                if (yellowTobeValidated != null) { // Nested-if for null checking nested objects
                    try {
                        if (!isValid32(yellowTobeValidated)) {
                            iterator.remove();
                            badEggFailureBucketMap.put(eggIndex, VALIDATION_FAILURE_32);
                        }
                    } catch (Exception e) {
                        iterator.remove();
                        badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
                        return;
                    }
                }
            } else {
                iterator.remove();
                badEggFailureBucketMap.put(eggIndex, VALIDATION_FAILURE_2);
            }
        } catch (Exception e) {
            iterator.remove();
            badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
            return;
        }
    }

    private boolean validate2(Map<Integer, ValidationFailure> badEggFailureBucketMap, int eggIndex, Iterator<Egg> iterator, Egg eggTobeValidated) {
        try {
            if (!isValid2(eggTobeValidated)) {
                iterator.remove();
                badEggFailureBucketMap.put(eggIndex, VALIDATION_FAILURE_2);
            }
        } catch (Exception e) { // Repetition of same logic for exception handling
            iterator.remove();
            badEggFailureBucketMap.put(eggIndex, ValidationFailure.withErrorMessage(e.getMessage()));
            return true;
        }
        return false;
    }

    private boolean validate1(Map<Integer, ValidationFailure> badEggFailureBucketMap, int eggIndex, Iterator<Egg> iterator, Egg eggTobeValidated) {
        if (!isValid1(eggTobeValidated)) {
            iterator.remove(); // Mutation
            // How do you cleanly map validation-failure to which validation-method failed?
            badEggFailureBucketMap.put(eggIndex, VALIDATION_FAILURE_1);
            return true;
        }
        return false;
    }

    private boolean isValid32(Yellow yellowTobeValidated) throws Exception {
        return true;
    }

    private boolean isValid31(Egg eggTobeValidated) throws Exception {
        return true;
    }

    private boolean isValid2(Egg eggTobeValidated) throws Exception {
        return true;
    }

    private boolean isValid1(Egg eggTobeValidated) {
        return true;
    }

}
