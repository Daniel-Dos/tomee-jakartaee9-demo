/*
 * The MIT License
 * Copyright © 2021 Daniel Dias
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.medium.danieldiasjava.jakarta.tomee.banner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

/**
 * @author Daniel Dias
 *
 */
@ApplicationScoped
public class Banner {

	@Inject
	private Logger logger;
	
	@Inject
	@ConfigProperty(name = "banner.file")
	private String banner;
	
	@Inject
	@ConfigProperty(name = "enable.banner")
	private Boolean enableBanner;
	
	public void init(@Observes @Initialized(ApplicationScoped.class) Object o ) throws FileNotFoundException {

		if (enableBanner) {

		var isr = "/banner.txt".equals(banner) ? new InputStreamReader(getClass().getResourceAsStream(banner)) 
				                               : new InputStreamReader(new FileInputStream(new File(banner)));
		try (var reader = new BufferedReader(isr)) {
			var bannerOut= reader.lines()
					          .collect(Collectors.joining(System.lineSeparator()));
			System.out.println(bannerOut);
		} catch (IOException e) {
			this.logger.error(e.getMessage());
		}
	  }
	}
}