package com.example.myapexapp;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	String name;
	int age;
	int id;
	HashMap<String, ArrayList<String>> map;
	Object ruleOutput;

	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setMap(HashMap<String, ArrayList<String>> map) {
		this.map = map;
	}
	
	public HashMap<String, ArrayList<String>> getMap() {
		return map;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public void setRuleOutput(Object o) {
		this.ruleOutput = o;
	}

	public Object getRuleOutput() {
		return ruleOutput;
	}

	public int getAge() {
		return age;
	}

	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

}