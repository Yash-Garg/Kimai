import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:injectable/injectable.dart';
import 'package:kimai/data/api/kimai_repository.dart';
import 'package:kimai/data/models/models.dart';
import 'package:kimai/data/models/screen_status.dart';

part 'home_state.dart';

@lazySingleton
class HomeCubit extends Cubit<HomeState> {
  final KimaiRepository _repository;

  HomeCubit({
    required KimaiRepository repository,
  })  : _repository = repository,
        super(HomeState());

  void initialize() async {
    emit(state.copyWith(status: ScreenStatus.loading));
    await loadUser();
    await loadProjects();
    await loadActivities();
    await loadCustomers();
    refresh();
  }

  void changePage(int index) {
    emit(state.copyWith(currentPageIndex: index));
  }

  Future<void> loadActivities() async {
    final response = await _repository.getActivities();

    response.fold(
      (activities) => emit(state.copyWith(activities: activities)),
      (err) => emit(state.copyWith(
        status: ScreenStatus.failed,
        error: err.toString(),
      )),
    );
  }

  Future<void> loadProjects() async {
    final response = await _repository.getProjects();

    response.fold(
      (projects) => emit(state.copyWith(projects: projects)),
      (err) => emit(state.copyWith(
        status: ScreenStatus.failed,
        error: err.toString(),
      )),
    );
  }

  Future<void> loadTimesheets() async {
    final response = await _repository.getTimeSheets();

    response.fold(
      (sheets) => emit(state.copyWith(sheets: sheets)),
      (err) => emit(state.copyWith(
        status: ScreenStatus.failed,
        error: err.toString(),
      )),
    );
  }

  Future<void> loadCustomers() async {
    final response = await _repository.getCustomers();

    response.fold(
      (customers) => emit(state.copyWith(customers: customers)),
      (err) => emit(state.copyWith(
        status: ScreenStatus.failed,
        error: err.toString(),
      )),
    );
  }

  Future<void> loadUser() async {
    final response = await _repository.getMyUser();

    response.fold(
      (user) => emit(state.copyWith(user: user)),
      (err) => emit(state.copyWith(
        status: ScreenStatus.failed,
        error: err.toString(),
      )),
    );
  }

  Future<void> refresh() async {
    await loadTimesheets();
    emit(state.copyWith(status: ScreenStatus.success));
  }
}
