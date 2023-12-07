public enum LearningStyle {
    ACTIVIST(
            "Activists want practical tasks and very little theory. They learn best from activities where:\n" +
                    "• New experiences are emphasised;\n" +
                    "• The focus is on the present and on doing such activities as games, problem solving, simulations;\n" +
                    "• There is a lot of action and excitement;\n" +
                    "• They can lead and be in the limelight;\n" +
                    "• Ideas are generated without any concern about practical constraints;\n" +
                    "• They have to respond to a challenge and take risks;\n" +
                    "• The central focus is on team problem-solving.\n"
    ),
    THEORIST(
            "Theorists want handouts, something to take away and study. They learn best from activities where:\n" +
                    "• The learning forms a part of a conceptual whole, such as a model for a theory;\n" +
                    "• There is time to explore the interrelationship amongst elements;\n" +
                    "• They can explore the theory and methodology underlying the subject under investigation;\n" +
                    "• They are intellectually stretched;\n" +
                    "• There is a clear and obvious purpose to the activities;\n" +
                    "• There is a reliance on rationality and logic;\n" +
                    "• They can analyse situations and then generalise their findings;\n" +
                    "• They are asked to understand complex situations.\n"
    ),
    REFLECTOR(
            "Reflectors want lots of breaks to go off and read and discuss. They learn best from activities where:\n" +
                    "• There are opportunities to observe and consider;\n" +
                    "• There is a strong element of passive involvement such as listening to a speaker or watching a video;\n" +
                    "• There is time to think before having to act or contribute;\n" +
                    "• There is opportunity for research and problems can be probed in some depth;\n" +
                    "• They can review what was happening;\n" +
                    "• They are asked to produce reports that carefully analyse a situation or issue;\n" +
                    "• There is interaction with others without any risks of strong feelings coming to the fore;\n" +
                    "• They can finalise a view without being put under pressure.\n"
    ),
    PRAGMATIST(
            "Pragmatists want shortcuts and tips. They learn best from activities where:\n" +
                    "• There is a clear link back to some job-related problem;\n" +
                    "• Material is directed towards techniques that make their work easier;\n" +
                    "• They are able to practice what they have learned;\n" +
                    "• They can relate to a successful role model;\n" +
                    "• There are many opportunities to implement what has been learned;\n" +
                    "• The relevance is obvious and the learning is easily transferred to their jobs;\n" +
                    "• What is done is practical such as drawing up action plans or trialing techniques or procedures.\n"
    );
    private final String description;
    private int score;
    LearningStyle(String description) {
        this.description = description;
        this.score = 0;
    }
    public String getDescription() {
        return description;
    }
    public int getScore() {
        return score;
    }
    public void addToScore(int value) {
        this.score += value;
    }
    public static void printStylePreference(String style, String description) {
        System.out.println(style + " preference: ");
        System.out.println(description);
    }
}

