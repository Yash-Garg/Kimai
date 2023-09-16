import 'dart:developer';
import 'package:flutter_bloc/flutter_bloc.dart';

class AppBlocObserver extends BlocObserver {
  @override
  void onEvent(Bloc bloc, Object? event) {
    log(
      'onEvent: $event',
      name: bloc.runtimeType.toString(),
    );
    super.onEvent(bloc, event);
  }

  @override
  void onError(BlocBase bloc, Object error, StackTrace stackTrace) {
    log(
      'onError: $error $stackTrace',
      name: bloc.runtimeType.toString(),
    );
    super.onError(bloc, error, stackTrace);
  }

  @override
  void onChange(BlocBase bloc, Change change) {
    log(
      '''
onChange: ${bloc.runtimeType}
currentState: ${change.currentState}
nextState: ${change.nextState}
    ''',
      name: bloc.runtimeType.toString(),
    );
    super.onChange(bloc, change);
  }

  @override
  void onClose(BlocBase bloc) {
    log(
      'onClose: ${bloc.runtimeType}',
      name: bloc.runtimeType.toString(),
    );
    super.onClose(bloc);
  }
}
