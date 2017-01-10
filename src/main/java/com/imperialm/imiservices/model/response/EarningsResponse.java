package com.imperialm.imiservices.model.response;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EarningsResponse{
		
		@Id
		private String territory;
		private BigDecimal moparParts;
		private BigDecimal mvp;
		private BigDecimal magnetiMarelli;
		private BigDecimal partsCounter;
		private BigDecimal expressLane;
		private BigDecimal wiAdvisor;
		private BigDecimal uConnect;
		public String getTerritory() {
			return territory;
		}
		public void setTerritory(String territory) {
			this.territory = territory;
		}
		public BigDecimal getMoparParts() {
			return moparParts;
		}
		public void setMoparParts(BigDecimal moparParts) {
			this.moparParts = moparParts;
		}
		public BigDecimal getMvp() {
			return mvp;
		}
		public void setMvp(BigDecimal mvp) {
			this.mvp = mvp;
		}
		public BigDecimal getMagnetiMarelli() {
			return magnetiMarelli;
		}
		public void setMagnetiMarelli(BigDecimal magnetiMarelli) {
			this.magnetiMarelli = magnetiMarelli;
		}
		public BigDecimal getPartsCounter() {
			return partsCounter;
		}
		public void setPartsCounter(BigDecimal partsCounter) {
			this.partsCounter = partsCounter;
		}
		public BigDecimal getExpressLane() {
			return expressLane;
		}
		public void setExpressLane(BigDecimal expressLane) {
			this.expressLane = expressLane;
		}
		public BigDecimal getWiAdvisor() {
			return wiAdvisor;
		}
		public void setWiAdvisor(BigDecimal wiAdvisor) {
			this.wiAdvisor = wiAdvisor;
		}
		public BigDecimal getuConnect() {
			return uConnect;
		}
		public void setuConnect(BigDecimal uConnect) {
			this.uConnect = uConnect;
		}
	
}
