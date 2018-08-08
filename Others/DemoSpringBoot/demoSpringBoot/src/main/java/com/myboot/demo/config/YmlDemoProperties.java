package com.myboot.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ymldemo")
public class YmlDemoProperties {
	
	private EnvDemo envDev;
	private EnvDemo envUat;	
	private String[] names;

	private List<MySqlDemo> mysqls = new ArrayList<MySqlDemo>();
	
	
	
	public EnvDemo getEnvDev() {
		return envDev;
	}


	public void setEnvDev(EnvDemo envDev) {
		this.envDev = envDev;
	}


	public EnvDemo getEnvUat() {
		return envUat;
	}


	public void setEnvUat(EnvDemo envUat) {
		this.envUat = envUat;
	}


	public String[] getNames() {
		return names;
	}


	public void setNames(String[] names) {
		this.names = names;
	}


	public List<MySqlDemo> getMysqls() {
		return mysqls;
	}


	public void setMysqls(List<MySqlDemo> mysqls) {
		this.mysqls = mysqls;
	}

	public static class EnvDemo{
		private String name;
		private String url;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	}

	public static class MySqlDemo{
		private String username;
		private String url;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	}
	
}

