package com.pcwk.ehr.google_chart.domain;

import com.pcwk.ehr.cmn.DTO;

public class PizzaDTO extends DTO {

	private String topping;
	private int slices;
	/**
	 * 
	 */
	public PizzaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param topping
	 * @param slices
	 */
	public PizzaDTO(String topping, int slices) {
		super();
		this.topping = topping;
		this.slices = slices;
	}
	/**
	 * @return the topping
	 */
	public String getTopping() {
		return topping;
	}
	/**
	 * @param topping the topping to set
	 */
	public void setTopping(String topping) {
		this.topping = topping;
	}
	/**
	 * @return the slices
	 */
	public int getSlices() {
		return slices;
	}
	/**
	 * @param slices the slices to set
	 */
	public void setSlices(int slices) {
		this.slices = slices;
	}
	@Override
	public String toString() {
		return "PizzaDTO [topping=" + topping + ", slices=" + slices + "]";
	}
	
	
	
	
}
