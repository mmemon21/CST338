// Author: Mustafa Memon
// File name: School.java
// Code purpose: This code is a class called School to work with other classes for Project1 CST338.

import java.util.*;
import java.io.*;
// import Instructor;
public class School{
	String schoolname;
	ArrayList<Instructor> instructors = new ArrayList<Instructor>();
	ArrayList<Student> students = new ArrayList<Student>();
	ArrayList<Course> courses = new ArrayList<Course>();
	School(String name){
		this.schoolname=name;
	}
	void readData(String file){
		try{
			int n=0;
			File f=new File(file);
			Scanner s = new Scanner(f);
			if(s.hasNext()){
				n=Integer.parseInt(s.nextLine());
				// System.out.println(n);
				for(int x=0;x<n;x++){
					String str=s.nextLine();
					String[] ins_arr= str.split(",");
					int num=Integer.parseInt(ins_arr[0]);
					String name=ins_arr[1];
					String email=ins_arr[2];
					String phone=ins_arr[3];
					Instructor i=new Instructor(num,name,email,phone);
					if(this.searchInstructorByNum(num)){
						System.out.println("Instructor info reading failed - Instructor number "+num+" already used");
					}
					else{
						instructors.add(i);
					}
				}
			}
			if(s.hasNext()){
				n=Integer.parseInt(s.nextLine());
				// System.out.println(n);
				for(int x=0;x<n;x++){
					String str=s.nextLine();
					String[] ins_arr= str.split(",");
					int cnum=Integer.parseInt(ins_arr[0]);
					String ctitle=ins_arr[1];
					int capacity=Integer.parseInt(ins_arr[2]);
					String class_loc=ins_arr[3];
					Course c=new Course(cnum,ctitle,capacity,class_loc);
					if(this.searchCourseByNum(cnum)){
						System.out.println("Course info reading failed - Course number "+cnum+" already used");
					}
					else{
						courses.add(c);
					}
				}
			}
			if(s.hasNext()){
				n=Integer.parseInt(s.nextLine());
				// System.out.println(n);
				for(int x=0;x<n;x++){
					String str=s.nextLine();
					String[] ins_arr= str.split(",");
					int id=Integer.parseInt(ins_arr[0]);
					String name=ins_arr[1];
					Student std=new Student(id,name);
					if(this.searchStudentById(id)){
						System.out.println("Student info reading failed - Student ID "+id+" already used");
					}
					else{
						students.add(std);
					}
				}
			}
			s.close();
			System.out.println("Done.");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	void schoolInfo(){
		System.out.println("School Name: "+this.schoolname);
		System.out.println("Instructor Information");
		for(Instructor i:instructors){
			System.out.println(i.name);
		}
		System.out.println("Course Information");
		for(Course c:courses){
			System.out.println(c.ctitle);
		}
		System.out.println("Student Information");
		for(Student s:students){
			System.out.println(s.name);
		}
	}
	void addInstructor(int num,String name,String email,String phone){
		if(this.searchInstructorByNum(num)){
			System.out.println("Instructor addition failed - Instructor number "+num+" already used");
		}
		else{
			Instructor i=new Instructor(num,name,email,phone);
			instructors.add(i);
			// System.out.println("Instructor added");
		}
	}
	void addCourse(int cnum,String ctitle,int capacity,String class_loc){
		if(this.searchCourseByNum(cnum)){
			System.out.println("Course addition failed - Course number "+cnum+" already used");
		}
		else{
			Course c=new Course(cnum,ctitle,capacity,class_loc);
			courses.add(c);
			// System.out.println("Course added");
		}
	}
	void addStudent(int id,String name){
		
		if(this.searchStudentById(id)){
			System.out.println("Student addition failed - Student ID "+id+" already used");
		}
		else{
			Student std=new Student(id,name);
			students.add(std);
		}
	}
	Course getCourse(int cnum){
		for(Course c:courses){
			if(c.cnum==cnum){
				return c;
			}
		}
		return null;
	}
	Instructor getInstructor(int num){
		for(Instructor i:instructors){
			if(i.number==num){
				return i;
			}
		}
		return null;
	}
	Student getStudent(int id){
		for(Student s:students){
			if(s.id==id){
				return s;
			}
		}
		return null;
	}

	void assignInstructor(int cnum,int inum){
		Course c=getCourse(cnum);
		Instructor i=getInstructor(inum);
		if(searchInstructorByNum(inum)){
			if(c!=null){
				c.instructor=i;
				i.courses.add(c);
				// System.out.println("Instructor assigned");
			}
		}
		else{
			System.out.println("Instructor "+inum+" does not exist");
		}
	}
	void register(int cnum,int id){
		Course c=getCourse(cnum);
		Student s=getStudent(id);
		if(c!=null){
			if(s==null){
				System.out.println("Student "+id+" does not exist");
			}
			else{
				if(c.registered_students.size()>=c.capacity){
					System.out.println("Registration failed - Class is full");
				}
				else{
					CourseScore cs=new CourseScore(id,c);
					c.registered_students.add(cs);
					s.score_card.add(cs);
				}
			}
		}
	}
	void unRegister(int cnum,int id){
		Course c=getCourse(cnum);
		Student s=getStudent(id);
		if(c!=null){
			CourseScore cs =c.getCSBySid(id);
			c.registered_students.remove(cs);
			s.score_card.remove(cs);
		}
	}
	void putScore(int cnum,int id, double score){
		// int index=c.registered_students.indexOf(id);
		// if(index!=null){
		// 	c.bind_score.set(index,score);
		// }
		// else{
		// 	if(s!=null){
		// 		System.out.println("Student "+id+" ("+s.name+") is not enrolled in "+cnum);
		// 	}
		// }
		Course c=getCourse(cnum);
		Student s=getStudent(id);
		if(c!=null){
			CourseScore cs =c.getCSBySid(id);
			if(cs==null){
				System.out.println("Student "+id+" ("+s.name+") is not enrolled in "+cnum);
			}
			else{
				cs.score=score;
			}
		}

	}
	void deleteCourse(int cnum){
		Course c=getCourse(cnum);
		if(c.registered_students.size()==0){
			courses.remove(c);
		}
		else{
			System.out.println("Course deletion failed - Enrolled students in class");
		}
	}
	void courseInfo(){
		System.out.println("Number of courses: "+(courses.size()));
		for(Course c:courses){
			System.out.println(c.cnum+": "+c.registered_students.size()+" enrolled");
		}
	}
	void courseInfo(int cnum){
		Course c=getCourse(cnum);
		if(c!=null){
			System.out.println(c);
		}
	}
	void graduateStudent(int id){
		Student s=getStudent(id);
		for(CourseScore cs:s.score_card){
			unRegister(cs.course.cnum,id);
		}
		students.remove(s);
	}
	boolean searchStudentById(int id){
		for(Student s:students){
			if(s.id==id){
				return true;
			}
		}
		return false;
	}
	boolean searchInstructorByNum(int num){
		for(Instructor i:instructors){
			if(i.number==num){
				return true;
			}
		}
		return false;
	}
	boolean searchCourseByNum(int num){
		for(Course c:courses){
			if(c.cnum==num){
				return true;
			}
		}
		return false;
	}
	Void searchByEmail(String email){
		System.out.println("Search Key: "+email);
		for(Instructor i:instructors){
			
			if(i.email.equals(email)){
				
				System.out.println("Employee Number "+i.number);
				System.out.println("Name: "+i.name);
				System.out.println("Phone: "+i.phone);
				return null;
			}
		}
		System.out.println("No employee with email "+email);
		return null;
	}
}
