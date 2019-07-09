package com.banka.editor.entity;


public class HoursByProject {

	private int id;
	private float hours;
	
	public HoursByProject() {
	}
	
	public HoursByProject(int id, float hours) {
		this.id = id;
		this.hours = hours;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getHours() {
		return hours;
	}
	public void setHours(float hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "HoursByProject [id=" + id + ", hours=" + hours + "]";
	}
	
	
}
