/**
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.developers.containers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "text/plain")
	public String hello() {
		String hostname = System.getenv().getOrDefault("HOSTNAME", "Unknown");
		return String.format("Hello world from %s", hostname);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/cpu", produces = "text/plain")
	public String cpu() {
		Runtime runtime = Runtime.getRuntime();

		int processors = runtime.availableProcessors();
		long maxMemory = runtime.maxMemory();

		return String.format("Number of processors: %d\nMax memory: %d bytes\n", processors, maxMemory);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/memory", produces = "text/plain")
	public String memory() {
		System.out.println("Starting to allocate memory...");
		Runtime rt = Runtime.getRuntime();
		StringBuilder sb = new StringBuilder();
		long maxMemory = rt.maxMemory();
		long usedMemory = 0;
    try{
  		while (((float) usedMemory / maxMemory) < 0.80) {
  			sb.append(System.nanoTime() + sb.toString());
  			usedMemory = rt.totalMemory();
  		}
    } catch (OutOfMemoryError e){
      // Do nothing as we expect it to happen
    } finally{
  		String msg = "Allocated more than 80% (" + humanReadableByteCount(usedMemory, false) + ") of the max allowed JVM memory size ("
  				+ humanReadableByteCount(maxMemory, false) + ")";
  		System.out.println(msg);
  		return msg;
    }
	}

	public static String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/health")
	public String health() {
		return "I'm ok";
	}

}
