import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:injectable/injectable.dart';
import 'package:kimai/data/api/kimai_repository.dart';
import 'package:kimai/data/models/auth_user.dart';
import 'package:kimai/data/models/ping.dart';
import 'package:kimai/data/models/screen_status.dart';
import 'package:kimai/data/models/field_status.dart';
import 'package:kimai/ui/common/bloc/authentication_bloc.dart';
import 'package:kimai/utils/api_result.dart';

part 'connection_state.dart';

@lazySingleton
class ConnectionCubit extends Cubit<ConnState> {
  final AuthenticationBloc _authenticationBloc;
  final KimaiRepository _repository;

  ConnectionCubit({
    required AuthenticationBloc authenticationBloc,
    required KimaiRepository repository,
  })  : _authenticationBloc = authenticationBloc,
        _repository = repository,
        super(ConnState.initial());

  void baseURLChanged(String value) {
    emit(state.copyWith(
      baseURL: value,
      baseURLStatus: FieldStatus.valid,
    ));
  }

  void usernameChanged(String value) {
    emit(state.copyWith(
      username: value,
      usernameStatus: FieldStatus.valid,
    ));
  }

  void apiKeyChanged(String value) {
    emit(state.copyWith(
      apiKey: value,
      apiKeyStatus: FieldStatus.valid,
    ));
  }

  void connect() async {
    emit(state.copyWith(status: ScreenStatus.loading));

    final user = AuthUser(
      baseUrl: state.baseURL,
      username: state.username,
      token: state.apiKey,
    );

    final result = await _repository.ping(user);

    result.fold(
      (ping) {
        _authenticationBloc.add(AuthenticationUserUpdated(user));
        emit(state.copyWith(status: ScreenStatus.success));
      },
      (err) => emit(state.copyWith(
        status: ScreenStatus.failed,
        error: err.toString(),
      )),
    );
  }
}
