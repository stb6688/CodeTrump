package com.codetrump.syntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Demonstrate the use case of Future framework.
 * @author Alex
 * @date Aug 22, 2016
 */
public class FutureTest {
	
	/**
	 * Simulate the server side to process a request.
	 * @author Alex
	 * @date Aug 22, 2016
	 */
	static class Server {
		private final Random random = new Random();
		
		/**
		 * process a request, which may take a while.
		 * @param reqId
		 * @return
		 */
		public long process(int reqId) {
			long start = System.currentTimeMillis();
			System.out.println("processing request " + reqId + "...");
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Finished request " + reqId);
			return System.currentTimeMillis() - start;
		}
	}
	
	/**
	 * Simulate client side, who creates a threads pool to submit requests.
	 * Each request() will return a future.
	 * @author Alex
	 * @date Aug 22, 2016
	 */
	static class Client {

		private final ExecutorService executor = Executors.newFixedThreadPool(10);
		private final Server server = new Server();
		
		// requestId must be final so that it can be passed into anonymous class Callable
		public Future<Long> request(final int reqId) {
			return executor.submit(new Callable<Long>(){
				public Long call(){
					return server.process(reqId);
				}
			});
		}
		
		public void shutdown() {
			executor.shutdown();
		}
	}
	
	
	public static void main(String[] args) {
		Client client = new Client();
		try {
			final int n = 5;
			List<Future<Long>> futs = new ArrayList<>(n);
			for (int i = 0; i < n; i++)
				futs.add(client.request(i));
			
			for (int i = 0; i < futs.size(); i++) {
				Future<Long> fut = futs.get(i);
				try {
					Long duration = fut.get(3, TimeUnit.SECONDS);
					System.out.println(String.format("request %d took %,dms", i, duration));
				} catch (TimeoutException e) {
					System.out.println("task timeout");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			client.shutdown();
		}
	}
	
}
