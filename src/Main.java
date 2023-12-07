import javax.swing.*;
import java.util.Random;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.sun.tools.javac.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.util.Arrays;

class KolbLeerstijlenTest {

    private static final int QUESTION_COUNT = 80;
    private static int currentQuestionIndex = 0;
    private static String[] userAnswers;

    public static void main(String[] args) {
        userAnswers = new String[QUESTION_COUNT];

        boolean isTesting = false; // Toggle for test purposes
        if (isTesting) {
            generateTestAnswers();
            processResults();
        } else {
            askQuestion();
        }
    }

    private static void askQuestion() {
        if (currentQuestionIndex < QUESTION_COUNT) {
            String[] options = {"Yes", "No", "Exit"};
            ImageIcon icon = new ImageIcon(KolbLeerstijlenTest.class.getResource("/resources/logo.jpeg"));

            int choice = JOptionPane.showOptionDialog(null, Questions.question[currentQuestionIndex],
                    "Question", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

            if (choice == JOptionPane.YES_OPTION) {
                userAnswers[currentQuestionIndex] = "yes";
            } else if (choice == JOptionPane.NO_OPTION) {
                userAnswers[currentQuestionIndex] = "no";
            } else if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                System.exit(0); // Afsluiten van het programma
            } else {
                JOptionPane.showMessageDialog(null, "Please select Yes, No, or Exit.");
                askQuestion();
                return;
            }

            currentQuestionIndex++;
            askQuestion();
        } else {
            processResults();
        }
    }

    private static void generateTestAnswers() {
        Random random = new Random();
        for (int i = 0; i < QUESTION_COUNT; i++) {
            userAnswers[i] = (random.nextBoolean()) ? "yes" : "no";
        }
    }

    private static void processResults() {
        ImageIcon logoIcon = new ImageIcon(KolbLeerstijlenTest.class.getResource("/resources/logo.jpeg"));
        int[] activistQuestionNumbers = {2, 4, 6, 10, 17, 23, 24, 32, 34, 38, 40, 43, 45, 48, 58, 64, 71, 72, 74, 79};
        int[] theoristQuestionNumbers = {1, 3, 8, 12, 14, 18, 20, 22, 26, 30, 42, 47, 51, 57, 61, 63, 68, 75, 77, 78};
        int[] reflectorQuestionNumbers = {7, 13, 15, 16, 25, 28, 29, 31, 33, 36, 39, 41, 46, 52, 55, 60, 62, 66, 67, 76};
        int[] pragmatistQuestionNumbers = {5, 9, 11, 19, 21, 27, 35, 37, 44, 49, 50, 53, 54, 56, 59, 65, 69, 70, 73, 80};

        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println();
        int activistScore = countMatches(userAnswers, activistQuestionNumbers);
        System.out.println("The activist score = " + activistScore);
        int theoristScore = countMatches(userAnswers, theoristQuestionNumbers);
        System.out.println("The theorist score = " + theoristScore);
        int reflectorScore = countMatches(userAnswers, reflectorQuestionNumbers);
        System.out.println("The reflector score = " + reflectorScore);
        int pragmatistScore = countMatches(userAnswers, pragmatistQuestionNumbers);
        System.out.println("The pragmatist score = " + pragmatistScore);
        System.out.println();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("The activist score = ").append(activistScore).append("\n").append(determineActivistPreference(activistScore)).append("\n\n");
        resultBuilder.append("The theorist score = ").append(theoristScore).append("\n").append(determineTheoristPreference(theoristScore)).append("\n\n");
        resultBuilder.append("The reflector score = ").append(reflectorScore).append("\n").append(determineReflectorPreference(reflectorScore)).append("\n\n");
        resultBuilder.append("The pragmatist score = ").append(pragmatistScore).append("\n").append(determinePragmatistPreference(pragmatistScore)).append("\n\n");

        resultBuilder.append("--------------------------------------------------------\n\n");
        resultBuilder.append("Learning Style Explanation:\n\n");

        for (LearningStyle style : LearningStyle.values()) {
            resultBuilder.append(style.name()).append("\n").append(style.getDescription()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, resultBuilder.toString(), "Test Results", JOptionPane.INFORMATION_MESSAGE, logoIcon);

        PDFGenerator.generatePDFWithOption(resultBuilder.toString());
    }


    private static void printLearningStyles() {
        for (LearningStyle style : LearningStyle.values()) {
            System.out.println(style.name() + "\n" + style.getDescription() + "\n");
        }
    }

    private static String determineActivistPreference(int score) {
        String preferenceLevel;
        if (score >= 13 && score <= 20) {
            preferenceLevel = "Very strong preference";
        } else if (score >= 11 && score <= 12) {
            preferenceLevel = "Strong preference";
        } else if (score >= 7 && score <= 10) {
            preferenceLevel = "Moderate preference";
        } else if (score >= 4 && score <= 6) {
            preferenceLevel = "Low preference";
        } else {
            preferenceLevel = "Very low preference";
        }
        System.out.println("Activist preference: " + preferenceLevel);
        return preferenceLevel;
    }
    private static String determineReflectorPreference(int score) {
        String preferenceLevel;
        if (score >= 18 && score <= 20) {
            preferenceLevel = "Very strong preference";
        } else if (score >= 15 && score <= 17) {
            preferenceLevel = "Strong preference";
        } else if (score >= 12 && score <= 14) {
            preferenceLevel = "Moderate preference";
        } else if (score >= 9 && score <= 11) {
            preferenceLevel = "Low preference";
        } else {
            preferenceLevel = "Very low preference";
        }
        System.out.println("Reflector preference: " + preferenceLevel);
        return preferenceLevel;
    }
    public static String determineTheoristPreference(int score) {
        String preferenceLevel;
        if (score >= 16 && score <= 20) {
            preferenceLevel = "Very strong preference";
        } else if (score >= 14 && score <= 15) {
            preferenceLevel = "Strong preference";
        } else if (score >= 11 && score <= 13) {
            preferenceLevel = "Moderate preference";
        } else if (score >= 8 && score <= 10) {
            preferenceLevel = "Low preference";
        } else {
            preferenceLevel = "Very low preference";
        }
        System.out.println("Theorist preference: " + preferenceLevel);
        return preferenceLevel;
    }
    private static String determinePragmatistPreference(int score) {
        String preferenceLevel;
        if (score >= 17 && score <= 20) {
            preferenceLevel = "Very strong preference";
        } else if (score >= 15 && score <= 16) {
            preferenceLevel = "Strong preference";
        } else if (score >= 12 && score <= 14) {
            preferenceLevel = "Moderate preference";
        } else if (score >= 9 && score <= 11) {
            preferenceLevel = "Low preference";
        } else {
            preferenceLevel = "Very low preference";
        }
        System.out.println("Pragmatist preference: " + preferenceLevel);
        return preferenceLevel;
    }
    public static int countMatches(String[] answers, int[] questionNumbers) {
        int counter = 0;
        for (int i : questionNumbers) {
            int index = i - 1;
            if ("yes".equals(answers[index])) {
                counter++;
            }
        }
        return counter;
    }
    //Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

}




