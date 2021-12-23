package day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Scanner {
	private Set<Beacon> beacons;
	private int[][] xRotate = {{1,0,0},{0,0,-1},{0,1,0}};
	private int[][] yRotate = {{0,0,1},{0,1,0},{-1,0,0}};
	private int[][] zRotate = {{0,-1,0},{1,0,0},{0,0,1}};
	public List<int[]> consumedScanners = new ArrayList<int[]>();
	
	public Scanner() {
		beacons = new HashSet<Beacon>();
	}
	
	public void addBeacon(Beacon beacon) {
		beacons.add(beacon);
	}
	
	public Set<Beacon> beacons(){
		return beacons;
	}
	
	public Scanner rotate(int[][] matrix){
		Scanner newScanner = new Scanner();
		for(Beacon beacon : beacons) {
			newScanner.addBeacon(beacon.rotate(matrix));
		}
		return newScanner;
	}
	
	public List<Scanner> allDirections(){
		List<Scanner> all = new ArrayList<Scanner>();
		Set<Beacon> beaconSet = beacons;
		Scanner newScanner = new Scanner();
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 4; y ++) {
				for(int z = 0; z < 4; z++) {
					for(Beacon beacon : beaconSet) {
						newScanner.addBeacon(beacon.rotate(zRotate));
					}
					beaconSet = newScanner.beacons();
					if(!all.contains(newScanner)) {
						all.add(newScanner);
					}
					newScanner = new Scanner();
				}
				for(Beacon beacon : beaconSet) {
					newScanner.addBeacon(beacon.rotate(yRotate));
				}
				beaconSet = newScanner.beacons();
				newScanner = new Scanner();
			}
			for(Beacon beacon : beaconSet) {
				newScanner.addBeacon(beacon.rotate(xRotate));
			}
			beaconSet = newScanner.beacons();
			newScanner = new Scanner();
		}
		return all;
	}
	
	public void addBeaconsFrom(Scanner other, int dx, int dy, int dz) {
		for(Beacon beacon : other.beacons) {
			Beacon adjustedBeacon = new Beacon(beacon.x+dx, beacon.y +dy, beacon.z +dz);
			boolean exists = false;
			for(Beacon b : beacons) {
				if(b.equals(adjustedBeacon)) {
					exists = true;
				}
			}
			if(!exists) {
				beacons.add(adjustedBeacon);
			}
		}
	}
	
	public Map<Beacon, Set<int[]>> distanceMap(){
		Map<Beacon, Set<int[]>> distances = new HashMap<Beacon, Set<int[]>>();
		for(Beacon beacon1 : beacons) {
			Set<int[]> distancesToOtherBeacons = new HashSet<int[]>();
			for(Beacon beacon2 : beacons) {
				if(!beacon1.equals(beacon2)) {
					distancesToOtherBeacons.add(beacon1.distancesTo(beacon2));
				}
			}
			distances.put(beacon1, distancesToOtherBeacons);
		}
		return distances;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Scanner) {
			return beacons.equals(((Scanner) other).beacons);
		} else {
			return false;
		}
	}
}
