package com.examination.timetabling;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        int[] intialSolution = new int[] {4, 5, 13, 1, 1, 4, 13, 2};
        String[][] examinationTimetabling = new String[4][4];
        String [] examTimetabling = new String[17];
        Arrays.fill(examTimetabling, "");
        Map<String, String[]> sameStudentsInExams = new HashMap<>();
        sameStudentsInExams.put("E1", new String[]{"E2","E4","E6"});
        sameStudentsInExams.put("E2", new String[]{"E1"});
        sameStudentsInExams.put("E4", new String[]{"E1","E5","E6","E8"});
        sameStudentsInExams.put("E5", new String[]{"E4","E8"});
        sameStudentsInExams.put("E6", new String[]{"E1","E4"});
        sameStudentsInExams.put("E8", new String[]{"E4","E5"});
        
        for (int i = 0; i < intialSolution.length; i++) {
        	int position = intialSolution[i];
        	int index = i + 1;
        	String exam = "E"+ index;
        	checkAndSetExam(examTimetabling, position, exam, sameStudentsInExams);
		}
        for (int i = 1; i < examTimetabling.length; i=i+4) {
        	System.out.println("9:00" + examTimetabling[i]);
        	System.out.println("11:00" + examTimetabling[i+1]);
        	System.out.println("2:00" + examTimetabling[i+2]);
        	System.out.println("4:00" + examTimetabling[i+3]);
		}
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
}
