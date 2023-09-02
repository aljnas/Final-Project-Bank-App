package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;
   class TestDemoJUnitTest {
	
	private TestDemo testDemo;
	@BeforeEach
	void setUp() throws Exception {
		  testDemo = new TestDemo();
	}
		
		public static Stream<Arguments>argumentsForAddPositive() {
	
			return Stream.of(Arguments.arguments(2, 4, 6,false),
					
					  
			           
			            Arguments.arguments(3, 6, 9, false),
			           Arguments.of(5, 2, 7, false), 
			           Arguments.arguments(1, 2, 3, false) 
			         //  Arguments.arguments(1, 2, 3, true)
			        );
			    }
		public static Stream<Arguments>argumentsForRestPositive() {
			
			return Stream.of(Arguments.arguments(9, 4, 5,false),
					
					  
			           
			            Arguments.arguments(14, 7, 0, true),
			           Arguments.of(-7, -70, 0, false) 
			            
			        );
			    }
		@Test
	    void assertThatPairsOfPositiveNumbersAreSubstractedCorrectly() {
	        assertThat(testDemo.restPositive(9, 5)).isEqualTo(4);
	        assertThat(testDemo.restPositive(44, 22)).isEqualTo(22);		}
   
	
		@Test
	    void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
	        assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
	        assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);		}
   
		@Test
		void assertThatNumberSquaredIsCorrect() {
			TestDemo mockDemo = spy(testDemo);
			doReturn(5).when(mockDemo).getRandomInt();
			int fiveSquared = mockDemo.randomNumberSquared();
			assertThat(fiveSquared).isEqualTo(25);
		}

		@ParameterizedTest
	    @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	    public void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
	        if (!expectException) {
	            assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
	        } else {
	            assertThatThrownBy(() -> testDemo.addPositive(a, b))
	                .isInstanceOf(IllegalArgumentException.class);
	        }
		}
		  
		  
}
