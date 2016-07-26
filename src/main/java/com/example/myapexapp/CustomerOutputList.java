package com.example.myapexapp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerOutputList {

	@XmlElement
	List<Customer> l;

	public List<Customer> getL() {
		if (l == null) {
			l = new ArrayList<Customer>();
		}
		return l;
	}

}
