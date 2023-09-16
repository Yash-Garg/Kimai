import 'package:equatable/equatable.dart';

import 'package:kimai/data/models/models.dart';
import 'package:kimai/data/models/screen_status.dart';

class TimesState extends Equatable {
  const TimesState({
    this.sheets = const [],
    this.activities = const [],
    this.projects = const [],
    this.status = ScreenStatus.initial,
    this.error,
  });

  final List<TimesheetActivity> sheets;
  final List<Activity> activities;
  final List<Activity> projects;
  final ScreenStatus status;
  final String? error;

  @override
  List<Object?> get props => [
        sheets,
        activities,
        projects,
        status,
        error,
      ];

  TimesState copyWith({
    List<TimesheetActivity>? sheets,
    List<Activity>? activities,
    List<Activity>? projects,
    ScreenStatus? status,
    String? error,
  }) {
    return TimesState(
      sheets: sheets ?? this.sheets,
      activities: activities ?? this.activities,
      projects: projects ?? this.projects,
      status: status ?? this.status,
      error: error,
    );
  }

  @override
  String toString() {
    return 'TimesState{sheets: $sheets, activities: $activities, projects: $projects, status: $status, error: $error}';
  }
}
