part of 'home_cubit.dart';

class HomeState extends Equatable {
  const HomeState({
    this.sheets = const [],
    this.activities = const [],
    this.customers = const [],
    this.projects = const [],
    this.status = ScreenStatus.initial,
    this.error,
    this.currentPageIndex = 0,
  });

  final List<TimesheetActivity> sheets;
  final List<Activity> activities;
  final List<Activity> projects;
  final List<Customer> customers;
  final ScreenStatus status;
  final String? error;
  final int currentPageIndex;

  @override
  List<Object?> get props => [
        sheets,
        activities,
        projects,
        customers,
        status,
        error,
        currentPageIndex,
      ];

  HomeState copyWith({
    List<TimesheetActivity>? sheets,
    List<Activity>? activities,
    List<Activity>? projects,
    List<Customer>? customers,
    ScreenStatus? status,
    String? error,
    int? currentPageIndex,
  }) {
    return HomeState(
      sheets: sheets ?? this.sheets,
      activities: activities ?? this.activities,
      projects: projects ?? this.projects,
      customers: customers ?? this.customers,
      status: status ?? this.status,
      error: error,
      currentPageIndex: currentPageIndex ?? this.currentPageIndex,
    );
  }
}
