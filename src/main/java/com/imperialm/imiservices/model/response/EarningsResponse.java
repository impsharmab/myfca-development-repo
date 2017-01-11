package com.imperialm.imiservices.model.response;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EarningsResponse{
		
		@Id
		private String territory;
		private double moparParts;
		private double mvp;
		private double magnetiMarelli;
		private double partsCounter;
		private double expressLane;
		private double wiAdvisor;
		private double uConnect;
		public String getTerritory() {
			return territory;
		}
		public void setTerritory(String territory) {
			this.territory = territory;
		}
		public double getMoparParts() {
			return moparParts;
		}
		public void setMoparParts(double moparParts) {
			this.moparParts = moparParts;
		}
		public double getMvp() {
			return mvp;
		}
		public void setMvp(double mvp) {
			this.mvp = mvp;
		}
		public double getMagnetiMarelli() {
			return magnetiMarelli;
		}
		public void setMagnetiMarelli(double magnetiMarelli) {
			this.magnetiMarelli = magnetiMarelli;
		}
		public double getPartsCounter() {
			return partsCounter;
		}
		public void setPartsCounter(double partsCounter) {
			this.partsCounter = partsCounter;
		}
		public double getExpressLane() {
			return expressLane;
		}
		public void setExpressLane(double expressLane) {
			this.expressLane = expressLane;
		}
		public double getWiAdvisor() {
			return wiAdvisor;
		}
		public void setWiAdvisor(double wiAdvisor) {
			this.wiAdvisor = wiAdvisor;
		}
		public double getuConnect() {
			return uConnect;
		}
		public void setuConnect(double uConnect) {
			this.uConnect = uConnect;
		}
		
}
