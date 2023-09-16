part of 'home_cubit.dart';

class HomeState extends Equatable {
  const HomeState({
    this.currentPageIndex = 0,
  });

  final int currentPageIndex;

  @override
  List<Object> get props => [currentPageIndex];

  HomeState copyWith({
    int? currentPageIndex,
  }) {
    return HomeState(
      currentPageIndex: currentPageIndex ?? this.currentPageIndex,
    );
  }

  factory HomeState.initial() {
    return const HomeState();
  }
}
