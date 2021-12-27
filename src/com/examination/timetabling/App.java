package com.examination.timetabling;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
        int[] initialSolution = new int[] {4, 5, 13, 1, 1, 4, 13, 2};
        String [] examTimetabling = new String[17];
        Arrays.fill(examTimetabling, "");
        Map<String, String[]> sameStudentsInExams = initializeConstraints();
        for (int i = 0; i < initialSolution.length; i++) {
        	int position = initialSolution[i];
        	int index = i + 1;
        	String exam = "E"+ index;
        	checkAndSetExam(examTimetabling, position, exam, sameStudentsInExams);
		}
        int dayIndex = 0;
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday"};
        printSolution(examTimetabling, dayIndex, days);
        
        mutation(examTimetabling, initialSolution, sameStudentsInExams);
        printSolution(examTimetabling, dayIndex, days);
    }

	private static void printSolution(String[] examTimetabling, int dayIndex, String[] days) {
		for (int i = 1; i < examTimetabling.length; i=i+4) {
        	System.out.println("\n" + days[dayIndex]);
        	++dayIndex;
        	System.out.println("9:00 " + examTimetabling[i]);
        	System.out.println("11:00 " + examTimetabling[i+1]);
        	System.out.println("2:00 " + examTimetabling[i+2]);
        	System.out.println("4:00 " + examTimetabling[i+3]);
		}
	}

	private static Map<String, String[]> initializeConstraints() {
		Map<String, String[]> sameStudentsInExams = new HashMap<>();
        sameStudentsInExams.put("E1", new String[]{"E2","E4","E6"});
        sameStudentsInExams.put("E2", new String[]{"E1"});
        sameStudentsInExams.put("E4", new String[]{"E1","E5","E6","E8"});
        sameStudentsInExams.put("E5", new String[]{"E4","E8"});
        sameStudentsInExams.put("E6", new String[]{"E1","E4"});
        sameStudentsInExams.put("E8", new String[]{"E4","E5"});
		return sameStudentsInExams;
	}
    
    private static void checkAndSetExam(String[] examTimetabling, int position, String exam, Map<String, String[]> sameStudentsInExams) {
		String[] sameExams = sameStudentsInExams.get(exam);
		boolean isNotPresent = !sameStudentsInExams.containsKey(exam) || sameStudentsInExams.get(exam) == null;
    	if(isNotPresent|| !Arrays.asList(sameExams).contains(examTimetabling[position])) {
			long count = 0; //Arrays.asList(examTimetabling).subList(0, position).stream().filter(item -> !item.isEmpty()).count();
    		examTimetabling[(int) (position + count)] = examTimetabling[(int) (position + count)] + exam;
		} else {
			position = position + 1; 
			checkAndSetExam(examTimetabling, position, exam, sameStudentsInExams);
		}
    }
    
    private static void mutation(String[] examTimetabling, int[] initialSolution, Map<String, String[]> sameStudentsInExams) {
    	Arrays.fill(examTimetabling, "");
    	int randomExam = new Random().nextInt(initialSolution.length);
    	int randomPosition = new Random().nextInt(examTimetabling.length);
    	initialSolution[randomExam] = randomPosition;
        for (int i = 0; i < initialSolution.length; i++) {
        	int position = initialSolution[i];
        	int index = i+1;
        	String exam = "E"+ index;
        	checkAndSetExam(examTimetabling, position, exam, sameStudentsInExams);
		}
    	
    }
}
