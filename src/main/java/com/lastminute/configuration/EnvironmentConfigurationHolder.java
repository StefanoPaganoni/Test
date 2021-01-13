package com.lastminute.configuration;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * @author s.paganoni
 * 
 *
 */

@Configuration
@Validated
public class EnvironmentConfigurationHolder {

	@Value("${lastminute.categories.exempt}")
	@NotNull private String exempt;
	
	public String[] getExempt() {
		String[] arrayExempt = exempt.split(",");
		return arrayExempt;
	}
	
	@Override
	public String toString() {
		return "EnvironmentConfigurationHolder [exempt=" + exempt + "]";
	}
	
}
