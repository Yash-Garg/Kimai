import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:kimai/data/api/kimai_repository.dart';
import 'package:kimai/data/models/models.dart';
import 'package:kimai/data/models/screen_status.dart';
import 'package:kimai/utils/api_result.dart';

import 'times_state.dart';

@lazySingleton
class TimesCubit extends Cubit<TimesState> {
  final KimaiRepository _repository;

  TimesCubit({
    required KimaiRepository repository,
  })  : _repository = repository,
        super(TimesState());

  void initialize() async {
    emit(state.copyWith(status: ScreenStatus.loading));
    refresh();
  }

  Future<void> refresh() async {
    await loadTimesheets();
    await loadProjects();
    await loadActivities();
    emit(state.copyWith(status: ScreenStatus.success));
  }

  Future<void> loadTimesheets() async {
    final response = await _repository.getTimeSheets();

    switch (response) {
      case Success<List<TimesheetActivity>>():
        emit(state.copyWith(sheets: response.value));
        break;
      case Failure<List<TimesheetActivity>>():
        emit(state.copyWith(
          status: ScreenStatus.failed,
          error: response.exception.toString(),
        ));
        break;
    }
  }

  Future<void> loadProjects() async {
    final response = await _repository.getProjects();

    switch (response) {
      case Success<List<Activity>>():
        emit(state.copyWith(projects: response.value));
        break;
      case Failure<List<Activity>>():
        emit(state.copyWith(
          status: ScreenStatus.failed,
          error: response.exception.toString(),
        ));
        break;
    }
  }

  Future<void> loadActivities() async {
    final response = await _repository.getActivities();

    switch (response) {
      case Success<List<Activity>>():
        emit(state.copyWith(activities: response.value));
        break;
      case Failure<List<Activity>>():
        emit(state.copyWith(
          status: ScreenStatus.failed,
          error: response.exception.toString(),
        ));
        break;
    }
  }
}
