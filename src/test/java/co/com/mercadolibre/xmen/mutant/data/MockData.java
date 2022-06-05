package co.com.mercadolibre.xmen.mutant.data;

import java.util.Arrays;
import java.util.List;

public class MockData {

    public static String[] dnaMutantHorizontalSuccess(){
        return new String[]{"ATGCGA", "AAGTGC", "ATATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    }

    public static String[] dnaMutantHorizontalFail(){
        return new String[]{"GTGCGA", "AAGTGC", "ATATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    }

    public static String[] dnaMutantObliqueLeftToRightSuccess(){
        return new String[]{"ATGCGA", "AATTGC", "ATATGT", "AGAATG", "CCCCTA", "TCACTG"};
    }

    public static String[] dnaMutantObliqueRightToLeftSuccess(){
        return new String[]{"ATGCGA", "AAGGAC", "TGGAGT", "GATGGG", "CCCTGA", "TCACTG"};
    }

    public static String[] dnaMutantObliqueFail(){
        return new String[]{"AAAAGA", "GGGGAC", "TTTTGT", "GGGGGG", "CCCCCA", "TCACTG"};
    }

    public static String[] dnaMutantVerticalSucess(){
        return new String[]{"AAAAGA", "GGGGAC", "TTTTGT", "GGGGGG", "CCCCCA", "TCACTG"};
    }

    public static String[] dnaMutantVerticalFail(){
        return new String[]{"AATAGA", "GGAGAC", "TTCTGT", "GGGGGG", "CCCACA", "TCACTG"};
    }

    public static String[] dnaHuman(){
        return new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCTTA", "TCACTG"};
    }

    public static String[] dnaHumanLengthFail(){
        return new String[]{"ATGC", "CAGT", "TTATGT", "AGAAGG", "CCCTTA", "TCACTG"};
    }

    public static String[] dnaHumanLetterFail(){
        return new String[]{"ATGCTD", "CAGTFP", "TTATGT", "AGAAGG", "CCCTTA", "TCACTG"};
    }

    public static List<String> getNitrogenBaseDna() {
        return Arrays.asList("AAAA","CCCC","TTTT","GGGG");
    }

}
