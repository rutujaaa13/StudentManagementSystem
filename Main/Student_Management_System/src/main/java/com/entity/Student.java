package com.entity;

public class Student {
	
	private int id;
	private String name;
	private double marks;
	private int rollNum;
	public Student(int id, String name, double marks, int rollNum) {
		super();
		this.id = id;
		this.name = name;
		this.marks = marks;
		this.rollNum = rollNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	public int getRollNum() {
		return rollNum;
	}
	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", marks=" + marks + ", rollNum=" + rollNum + "]";
	}
	
	

}
