package intervals;

// Java Class Implementation of Intervals.
//
// Description:
//	Allows for the creation of versatile interval objects that can be of many different types efficiently.
//																    Examples
//	Each interval has a 'low' and 'high' boundary, and a potential 'pointer' to another interval.			   |
//	For a standard interval, only the 'low' and 'high' fields are filled, with the pointer set to null.		   |         [2, 5]
//	For an interval with a union, the 'pointer' will initialize a second interval, simulating an interval with a union.|	 [2, 5] U [7, 10]
//															   |
//	Similarly for standard intervals with unions, a NEGATIVE interval will simply be an interval with a Union,	   |  (-INF, 5] U [7, INF)
//	with its lowest boundary and highest boundaries having negative values.
//
//	Made By:
//		Jonathan Rivera
//		rivejona@kean.edu
//
//	For:
//		CAHSI's VREU Summer Research 2020
//

public class Interval {
		
	private double low;
	private double high;
	private Interval union;
	
	
// CONSTRUCTORS		
  public Interval(double low, double high) {
	  this.low = low;
	  this.high = high;
	  union = null;
	  equalize();
  }

  public Interval(double low, double high, double low2, double high2) {
	  this.low = low;
	  this.high = high;
	  union = new Interval(low2, high2);
	  equalize();
  }

  public Interval(double low, double high, double low2, double high2, double low3, double high3) {
	  this.low = low;
	  this.high = high;
	  union = new Interval(low2, high2, low3, high3);
  }

  public Interval(double low, double high, double low2, double high2, double low3, double high3, double low4, double high4) {
	  this.low = low;
	  this.high = high;
	  union = new Interval(low2, high2, low3, high3, low4, high4);
	  equalize();
  }

  public Interval(Interval x, double low, double high) {
	  this.low = x.getLow();
	  this.high = x.getHigh();
	  union = new Interval(low, high);
	  equalize();
  }

  public Interval(Interval x, Interval y) {
	  this.low = x.getLow();
	  this.high = x.getHigh();
	  if(x.hasUnion())
		  union = new Interval(x.getUnion(), y);
	  else
		  union = y;
  }
	

	public double getLow()	{	return low;	}
	public double getHigh()	{	return high;	}
	public Interval getUnion() {	return union;	}
	
	
	// OPERATIONS
	public Interval add(Interval x) { 
		
		// [a, b] + [c, d]
		if(union == null && !x.hasUnion())		return new Interval(low + x.getLow(), high + x.getHigh());
		
		// [a, b]U[c,d] + [e, f]
		if(union != null && !x.hasUnion())		return new Interval(low + x.getLow(), high + x.getHigh(), union.getLow() + x.getLow(), union.getHigh() + x.getHigh());
		
		// [a, b] + [c, d]U[e, f]
		if(union == null && x.hasUnion())		return new Interval(x.getLow() + low, x.getHigh() + high, x.getUnion().getLow() + low, x.getUnion().getHigh() + high);
		
		// [a, b]U[c,d] + [e, f]U[g, h]
		return new Interval(	low + x.getLow(), high + x.getHigh(), union.getLow() + x.getLow(), union.getHigh() + x.getHigh(),
					low + x.getUnion().getLow(), high + x.getUnion().getHigh(), union.getLow() + x.getUnion().getLow(), union.getHigh() + x.getUnion().getHigh());
		
	}
	
	
	public Interval subtract(Interval x)	{

		// [a, b] - [c, d]
		if(union == null && !x.hasUnion())		return new Interval(low - x.getHigh(), high - x.getLow());

		// [a, b]U[c,d] - [e, f]
		if(union != null && !x.hasUnion())		return new Interval(low - x.getHigh(), high - x.getLow(), union.getLow() - x.getHigh(), union.getHigh() - x.getLow());

		// [a, b] - [c, d]U[e, f]
		if(union == null && x.hasUnion())		return new Interval(x.getLow() - high, x.getHigh() - low, x.getUnion().getLow() - high, x.getUnion().getHigh() - low);

		// [a, b]U[c,d] - [e, f]U[g, h]
		return new Interval(	low - x.getHigh(), high - x.getLow(), union.getLow() - x.getHigh(), union.getHigh() - x.getLow(),
					low - x.getUnion().getHigh(), high - x.getUnion().getLow(), union.getLow() - x.getUnion().getHigh(), union.getHigh() + x.getUnion().getLow());
	}
	
	
	public Interval multiply(Interval x) {
		
		double min  = Math.min(  Math.min( low * x.getLow(), low * x.getHigh()), Math.min( high * x.getLow(), high * x.getHigh()));
		double max = Math.max( Math.max(low * x.getLow(), low * x.getHigh()), Math.max(high * x.getLow(), high * x.getHigh()));
		
		// [a, b] * [c, d]
		if(union == null && !x.hasUnion())	return new Interval(min, max);

		// [a, b]U[c,d] * [e, f]
		if(union != null && !x.hasUnion())	return new Interval(min, max,
									    Math.min(  Math.min( union.getLow() * x.getLow(), union.getLow() * x.getHigh()), Math.min( union.getHigh() * x.getLow(), union.getHigh() * x.getHigh())),
									    Math.max( Math.max(union.getLow() * x.getLow(), union.getLow() * x.getHigh()), Math.max(union.getHigh() * x.getLow(), union.getHigh() * x.getHigh())));

		// [a, b] * [c, d]U[e, f]
		if(union == null && x.hasUnion())	return new Interval(min, max,
									    Math.min(  Math.min( low * x.getUnion().getLow(), low * x.getUnion().getHigh()), Math.min( high * x.getUnion().getLow(), high * x.getUnion().getHigh())),
									    Math.max( Math.max(low * x.getUnion().getLow(), low * x.getUnion().getHigh()), Math.max(low  * x.getUnion().getLow(), low  * x.getUnion().getHigh())));

		// [a, b]U[c,d] * [e, f]U[g, h]
		return new Interval(min, max,	// [a, b]U[c, d] * [e, f]
					Math.min(  Math.min( union.getLow() * x.getLow(), union.getLow() * x.getHigh()), Math.min( union.getHigh() * x.getLow(), union.getHigh() * x.getHigh())),
					Math.max( Math.max(union.getLow() * x.getLow(), union.getLow() * x.getHigh()), Math.max(union.getHigh() * x.getLow(), union.getHigh() * x.getHigh())),
										
						//[a, b]U[c, d] * [g, h]
					Math.min(  Math.min(  low * x.getUnion().getLow(), low * x.getUnion().getHigh()), Math.min( high * x.getUnion().getLow(), high * x.getUnion().getHigh())),
					Math.max(  Math.max(low * x.getUnion().getLow(), low * x.getUnion().getHigh()), Math.max(high * x.getUnion().getLow(), high * x.getUnion().getHigh())),
					Math.min(  Math.min( union.getLow() * x.getUnion().getLow(), union.getUnion().getLow() * x.getUnion().getHigh()), Math.min( union.getHigh() * x.getUnion().getLow(), union.getHigh() * x.getUnion().getHigh())),
					Math.max( Math.max(union.getLow() * x.getUnion().getLow(), union.getUnion().getLow() * x.getUnion().getHigh()), Math.max(union.getHigh() * x.getUnion().getLow(), union.getHigh() * x.getUnion().getHigh())));
	}
	
	
	public Interval divide(Interval x) throws ArithmeticException {
		
		// [a, b] / [c, d]
		if(union == null && !x.hasUnion())		return multiply(x.inverse());

		// [a, b]U[c,d] / [e, f]
		if(union != null && !x.hasUnion())		return new Interval(multiply(x.inverse()), union.multiply(x.inverse()));

		// [a, b] / [c, d]U[e, f]
		Interval xNoUnion = new Interval(x.getLow(), x.getHigh());
		Interval xUnion = new Interval(x.getUnion().getLow(), x.getUnion().getHigh());
		
		if(union == null && x.hasUnion())		return new Interval(multiply(xNoUnion.inverse()) , multiply(xUnion.inverse()));

		// [a, b]U[c,d] / [e, f]U[g, h]
		return new Interval( new Interval(multiply(x.inverse()), union.multiply(x.inverse())), new Interval(multiply(x.getUnion().inverse()), union.multiply(x.getUnion().inverse())));
	}
	
	
	// Right now, doesn't account for Union. Should be fine for current use, might want to look into it for the future.
	public Interval inverse() throws ArithmeticException {
		if(low == 0 || high == 0) throw new ArithmeticException("Divide by Zero");
		return low < 0 && high > 0 ? new Interval(Double.NEGATIVE_INFINITY, 1/low, 1/high, Double.POSITIVE_INFINITY) : new Interval(1/low, 1/high); 
   }
	
	
	public Interval negative() {
		return low == high ? new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY) : new Interval(Double.NEGATIVE_INFINITY, low, high, Double.POSITIVE_INFINITY);
	}
	
	// Method used to fix interval problems such as ordering, and can merge unions if their cases allow
	private void equalize() {
		if(low > high) {
			double	temp	= low;
						low	= high;
						high	= temp;
		}
		
		if(union == null) return; // Kill method early for efficiency if the interval doesn't have a union. Everything else from here is union checks [a, b]U[c,d] 

		
		if(high >= union.getLow() && union.getHigh() > high && !union.hasUnion()) {		// High-U.Low Overlap 	[1, 2]U[2, 3] --> [1, 3]
			high = union.getHigh();
			union = null;
			return;
		}
		else if(high >= union.getLow() && union.getHigh() <= high && !union.hasUnion()) {	// U.Encapsulation	[1, 5]U[2, 4] --> [1, 5]
			if(union.getLow() < low)
				low = union.getLow();
			union = null;
			return;
		}
		
		else if(low >= union.getLow() && high > union.getHigh() && !union.hasUnion()) {		// U.Low-Low Overlap	[50, 120]U[40, 80] --> [40, 120]
			low = union.getLow();
			union = null;
			return;
		}
		else if(low > union.getLow() && union.getHigh() >= high && !union.hasUnion()) {		//  Encapsulation	[50, 120]U[40, 150] --> [40, 150]
			low = union.getLow();
			high = union.getHigh();
			union = null;
			return;
		}
		
		if(!union.hasUnion()) return; // Short circuit if the interval's union doesn't have a union. Everything else from here is double union checks [a,b]U[c,d] U [e,f]
		
		// [a, b]U[c, d]U[e, f] OR [a, b]U[c, d]U[e, f]U[g, h]
		
		// My Solution: Truncate each interval into just [a, b] with no unions. Sort them by a, and then try to merge. If not, return everything as one interval with Unions.
		
		final java.util.List<double[]> intervals = new java.util.ArrayList<double[]>();
		int i = 0;
		for(Interval x = this; x != null; x = x.getUnion(), i++)
			intervals.add(new double[] {x.getLow(), x.getHigh()});
		
		// Sort each interval by starts. Example: This can bring it to [1, 2]U[1, 5]U[2, 4]U[6, 8]
		java.util.Collections.sort(intervals, new java.util.Comparator<double[]>(){ 
			public int compare(double[] arg0, double[] arg1) {
				return (int)(arg0[0] - arg1[0]);
			} 
        	});
		
		
		// Deal with cases. Simplify and fix order problems wherever possible
        	for(i = 0; i < intervals.size()-1; i++)
        		if(intervals.get(i)[0] == intervals.get(i+1)[0]) {	// [1,2] U [1, 3] || [1, 3] U [1, 2]  --> [1, 3]  Comparing first low to second low
        		
        		if(intervals.get(i)[1] >= intervals.get(i+1)[1])	
        			intervals.set(i, new double[] {intervals.get(i)[0], intervals.get(i)[1]});
        		else
        			intervals.set(i, new double[] {intervals.get(i)[0], intervals.get(i+1)[1]});
        		
        		intervals.remove(i+1);
        		i--;
        	}
        	
        	else if(intervals.get(i)[1] >= intervals.get(i+1)[0]) {		// [1, 2] U [2, 3] --> [1, 3]	Comparing first high to second low
        		
        		if(intervals.get(i)[1] < intervals.get(i+1)[1])
        			intervals.set(i, new double[] {intervals.get(i)[0], intervals.get(i+1)[1]});         // [1, 4] U [3, 5] --> [1, 5]
        		
        		intervals.remove(i+1);									     // Also deals with [1, 50] U [2, 30]  
        		i--;
        	}
        		
        switch(intervals.size()) {
        	case 1:	low = intervals.get(0)[0]; high = intervals.get(0)[1]; union = null; break;
        	case 2:	low = intervals.get(0)[0]; high = intervals.get(0)[1]; union = new Interval(intervals.get(1)[0], intervals.get(1)[1]); break;
        	case 3:	low = intervals.get(0)[0]; high = intervals.get(0)[1]; union = new Interval(intervals.get(1)[0], intervals.get(1)[1], intervals.get(2)[0], intervals.get(2)[1]); break;
        	case 4:	low = intervals.get(0)[0]; high = intervals.get(0)[1]; union = new Interval(intervals.get(1)[0], intervals.get(1)[1], intervals.get(2)[0], intervals.get(2)[1], intervals.get(3)[0], intervals.get(3)[1]); break;
        	default: System.out.println("Error. Check Equalize.");
        }
		
	} // end of equalize()
	
	public boolean hasUnion() {
		return union == null ? false : true;
	}
	
	public String toString() {
		if(union == null) 												return leftHelper(low) + rightHelper(high);
		else if(union != null && !union.hasUnion())	return leftHelper(low) + rightHelper(high) + "U" + leftHelper(union.getLow()) + rightHelper(union.getHigh());
		return leftHelper(low) + rightHelper(high) + "U" + leftHelper(union.getLow()) + rightHelper(union.getHigh()) + "U" + union.getUnion().toString();
	}
	
	private String leftHelper(double x) {	// toString() helper method to make things look neater. For low / Left Values
		if(x == Double.NEGATIVE_INFINITY)
			return "(-INF, ";
		
		if(x == (int)x)
			return "[" + (int)x + ", ";
		
		return "[" + x + ", ";
	}
	
	private String rightHelper(double x) {	// toString() helper method. For high / Right Values
		if(x == Double.POSITIVE_INFINITY)
			return "INF)";
		
		if(x == (int)x)
			return (int)x + "]";
		
		return x + "]";
	}
	
	
}
