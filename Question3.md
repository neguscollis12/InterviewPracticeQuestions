# Question 3 — Parking Lot Management System

Implement a parking lot that assigns spots to vehicles by size class and tracks entry/exit.

## Requirements

- The lot has a fixed number of Small, Medium, and Large spots.
- A vehicle has a size class; it can only park in a spot of its own size class or larger.
  - Small → any spot
  - Medium → Medium or Large
  - Large → Large only
- A vehicle cannot enter if it's already parked.
- A vehicle cannot exit if it isn't parked.
- Parking always uses the smallest available spot class that fits the vehicle.
- If no suitable spot is available, parking fails.

## Messages to Return

| Status | Message |
|---|---|
| Park success | `<plate> parked in <spotClass> spot successfully!` |
| Park fail: already parked | `<plate> is already parked!` |
| Park fail: lot full | `<plate> could not find an available spot!` |
| Exit success | `<plate> exited successfully!` |
| Exit fail: not parked | `<plate> is not parked!` |

## Function Description

### Vehicle Class — implements IVehicle

- constructor: `(plate, sizeClass)` where `sizeClass ∈ {Small, Medium, Large}`
- properties: `Plate`, `SizeClass`, `IsParked` (bool), `AssignedSpotClass`

### ParkingLot Class — implements IParkingLot

- constructor: `(smallSpots, mediumSpots, largeSpots)`
- properties: `AvailableSmall`, `AvailableMedium`, `AvailableLarge`
- `Park(IVehicle vehicle) → string`
- `Exit(IVehicle vehicle) → string`

## Input Format for Custom Testing

```text
smallSpots mediumSpots largeSpots
m                                  // number of vehicles
m lines of: plate, sizeClass
k                                  // number of operations
k lines of: FunctionName:vehicleIndex
```

### Sample Input

```text
1 1 0
2
ABC123, Small
XYZ999, Medium
3
Park:0
Park:1
Park:1
```

### Sample Output

```text
ABC123 parked in Small spot successfully!
XYZ999 parked in Medium spot successfully!
XYZ999 is already parked!
```

## Notes

- A vehicle should be considered parked only once it is assigned a valid spot.
- `AssignedSpotClass` should be updated when a vehicle parks and cleared when it exits.
- Parking must search in priority order: Small → Medium → Large for compatible classes.
