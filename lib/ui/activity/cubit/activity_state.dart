part of 'activity_cubit.dart';

class ActivityState extends Equatable {
  final ScreenStatus status;

  final List<Activity> activities;
  final List<Activity> projects;
  final List<Customer> customers;
  final DateTime date;
  final material.TimeOfDay startTime;
  final Duration duration;
  final Customer? customer;
  final Activity? project;
  final Activity? activity;
  final String? error;

  const ActivityState({
    required this.status,
    required this.activities,
    required this.projects,
    required this.customers,
    required this.date,
    required this.startTime,
    required this.duration,
    this.customer,
    this.project,
    this.activity,
    this.error,
  });

  List<DropdownMenuEntry<Duration>> get durationEntries => durations
      .map((key, value) => MapEntry(
            key,
            DropdownMenuEntry(
              label: key,
              value: value,
            ),
          ))
      .values
      .toList();

  List<DropdownMenuEntry<int>> get customerEntries => customers
      .map((c) => DropdownMenuEntry(
            label: c.name,
            value: c.id,
          ))
      .toList();

  List<DropdownMenuEntry<int>> get projectEntries => projects
      .map((p) => DropdownMenuEntry(
            label: p.name,
            value: p.id,
          ))
      .toList();

  List<DropdownMenuEntry<int>> get activityEntries => activities
      .map((p) => DropdownMenuEntry(
            label: p.name,
            value: p.id,
          ))
      .toList();

  factory ActivityState.initial() => ActivityState(
        status: ScreenStatus.initial,
        activities: const [],
        projects: const [],
        customers: const [],
        date: DateTime.now(),
        startTime: material.TimeOfDay.now(),
        duration: durations.values.first,
      );

  @override
  List<Object?> get props => [
        status,
        activities,
        projects,
        customers,
        date,
        startTime,
        duration,
        customer,
        project,
        activity,
        error,
      ];

  @override
  String toString() {
    return '''ActivityState {
      status: $status,
      activities: ${activities.length},
      projects: ${projects.length},
      customers: ${customers.length},
      date: $date,
      startTime: $startTime,
      duration: $duration,
      customer: $customer,
      project: $project,
      activity: $activity,
      error: $error,
    }''';
  }

  ActivityState copyWith({
    ScreenStatus? status,
    List<Activity>? activities,
    List<Activity>? projects,
    List<Customer>? customers,
    DateTime? date,
    material.TimeOfDay? startTime,
    Duration? duration,
    Customer? customer,
    Activity? project,
    Activity? activity,
    String? error,
  }) {
    return ActivityState(
      status: status ?? this.status,
      activities: activities ?? this.activities,
      projects: projects ?? this.projects,
      customers: customers ?? this.customers,
      date: date ?? this.date,
      startTime: startTime ?? this.startTime,
      duration: duration ?? this.duration,
      customer: customer ?? this.customer,
      project: project ?? this.project,
      activity: activity ?? this.activity,
      error: error,
    );
  }
}
