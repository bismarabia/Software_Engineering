package assignment3;

public class ClassHistory {

    private String[] dict;
    private ClassStats classStats;

    public ClassHistory(String[] dict) {
        this.dict = dict;
    }

    public String[] generate_project_history(){

        String[] project_history = new String[this.dict.length];
        for (int i = 0; i < dict.length; i++) {
            String file = dict[i];
            classStats = new ClassStats(file);
            file = classStats.file_name();
            project_history[i] = file+" ("+
                    String.valueOf(classStats.count_lines())+")";
        }

        return project_history;
    }

    public String[] generate_method_history(){

        String[] method_history = new String[this.dict.length];
        for (int i = 0; i < dict.length; i++){
            String file = dict[i];
            classStats = new ClassStats(file);
            method_history[i] = file+" ("+String.valueOf(classStats.count_methods())
                        +", "+String.valueOf(classStats.method_average())+")";
        }

        return method_history;
    }

    public float calculate_average_size(){

        if (dict.length==0) return 0;
        int count_method = 0;
        int loc = 0;
        for (String file : dict) {
            classStats = new ClassStats(file);
            count_method += classStats.count_methods();
            loc += classStats.method_average();
        }

        return count_method == 0? 0: (float) (1.0*loc/count_method);
    }

    public static void main(String[] re){
        String[] class_dict = new String[]{"E://Statistics.java", "E://ClassStats.java"};
        ClassHistory classHistory = new ClassHistory(class_dict);
        System.out.println(classHistory.calculate_average_size());

    }
}
