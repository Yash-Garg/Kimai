import 'dart:developer';

import 'package:flutter/material.dart' as material;
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:injectable/injectable.dart';
import 'package:kimai/data/api/kimai_repository.dart';
import 'package:kimai/data/models/models.dart';
import 'package:kimai/data/models/screen_status.dart';
import 'package:kimai/ui/common/dropdown.dart';

part 'activity_state.dart';

const durations = <String, Duration>{
  '00:15': Duration(minutes: 15),
  '00:30': Duration(minutes: 30),
  '00:45': Duration(minutes: 45),
  '01:00': Duration(hours: 1),
  '01:15': Duration(hours: 1, minutes: 15),
  '01:30': Duration(hours: 1, minutes: 30),
  '01:45': Duration(hours: 1, minutes: 45),
  '02:00': Duration(hours: 2),
  '02:15': Duration(hours: 2, minutes: 15),
  '02:30': Duration(hours: 2, minutes: 30),
  '02:45': Duration(hours: 2, minutes: 45),
  '03:00': Duration(hours: 3),
  '03:15': Duration(hours: 3, minutes: 15),
  '03:30': Duration(hours: 3, minutes: 30),
  '03:45': Duration(hours: 3, minutes: 45),
  '04:00': Duration(hours: 4),
  '04:15': Duration(hours: 4, minutes: 15),
  '04:30': Duration(hours: 4, minutes: 30),
  '04:45': Duration(hours: 4, minutes: 45),
  '05:00': Duration(hours: 5),
  '05:15': Duration(hours: 5, minutes: 15),
  '05:30': Duration(hours: 5, minutes: 30),
  '05:45': Duration(hours: 5, minutes: 45),
  '06:00': Duration(hours: 6),
  '06:15': Duration(hours: 6, minutes: 15),
  '06:30': Duration(hours: 6, minutes: 30),
  '06:45': Duration(hours: 6, minutes: 45),
  '07:00': Duration(hours: 7),
  '07:15': Duration(hours: 7, minutes: 15),
  '07:30': Duration(hours: 7, minutes: 30),
  '07:45': Duration(hours: 7, minutes: 45),
  '08:00': Duration(hours: 8),
  '08:15': Duration(hours: 8, minutes: 15),
  '08:30': Duration(hours: 8, minutes: 30),
  '08:45': Duration(hours: 8, minutes: 45),
  '09:00': Duration(hours: 9),
  '09:15': Duration(hours: 9, minutes: 15),
  '09:30': Duration(hours: 9, minutes: 30),
  '09:45': Duration(hours: 9, minutes: 45),
  '10:00': Duration(hours: 10),
};

@lazySingleton
class ActivityCubit extends Cubit<ActivityState> {
  final KimaiRepository _repository;

  ActivityCubit({
    required KimaiRepository repository,
  })  : _repository = repository,
        super(ActivityState.initial());

  void initialize({
    required List<Activity> activities,
    required List<Activity> projects,
    required List<Customer> customers,
  }) {
    emit(state.copyWith(
      activities: activities,
      projects: projects,
      customers: customers,
    ));
  }

  void setDate(DateTime date) {
    emit(state.copyWith(date: date));
  }

  void setStartTime(material.TimeOfDay startTime) {
    emit(state.copyWith(startTime: startTime));
  }

  void setDuration(Duration duration) {
    emit(state.copyWith(duration: duration));
  }

  void setCustomer(int id) {
    final customer = state.customers.firstWhere((e) => e.id == id);
    emit(state.copyWith(customer: customer));
  }

  void setProject(int id) {
    final project = state.projects.firstWhere((e) => e.id == id);
    emit(state.copyWith(project: project));
  }

  void setActivity(int id) {
    final activity = state.activities.firstWhere((e) => e.id == id);
    emit(state.copyWith(activity: activity));
  }

  void save() async {
    emit(state.copyWith(status: ScreenStatus.loading));

    if (state.activity == null ||
        state.project == null ||
        state.customer == null) {
      emit(state.copyWith(
        status: ScreenStatus.failed,
        error: 'Please select all fields!',
      ));
      return;
    }

    final date = state.date;
    final time = state.startTime;

    final startDt = DateTime(
      date.year,
      date.month,
      date.day,
      time.hour,
      time.minute,
    );

    final endDt = startDt.add(state.duration);

    final response = await _repository.createTimeSheet(
      begin: startDt.toIso8601String(),
      end: endDt.toIso8601String(),
      project: state.project!.id,
      activity: state.activity!.id,
    );

    response.fold(
      (sheet) {
        log(sheet.toString(), name: 'ActivityCubit');
        emit(state.copyWith(status: ScreenStatus.success));
      },
      (err) => emit(state.copyWith(
        status: ScreenStatus.failed,
        error: err.toString(),
      )),
    );
  }
}
