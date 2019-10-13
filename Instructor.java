// Author: Mustafa Memon
// File name: Instructor.java
// Code purpose: This code is a class called Instructor to work with other classes for Project1 CST338.

import java.util.*;
public class Instructor{
	int number;
	String name;
	String email;
	String phone;
	ArrayList<Course> courses=new ArrayList<Course>();
	Instructor(int number, String name, String email, String phone){
		this.number=number;
		this.name=name;
		this.email=email;
		this.phone=phone;
	}
	public String toString(){
		String s="";
		for(Course c:courses){
			s+=String.format(c.cnum+": "+c.registered_students.size()+"enrolled\n");
		}
		return String.format("Instructor Number: "+this.number+"\nName: "+this.name+"\nCourses Teaching: \n"+s);
	}
}