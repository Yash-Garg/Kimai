// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// InjectableConfigGenerator
// **************************************************************************

// ignore_for_file: unnecessary_lambdas
// ignore_for_file: lines_longer_than_80_chars
// coverage:ignore-file

// ignore_for_file: no_leading_underscores_for_library_prefixes
import 'package:dio/dio.dart' as _i3;
import 'package:get_it/get_it.dart' as _i1;
import 'package:injectable/injectable.dart' as _i2;
import 'package:isar/isar.dart' as _i4;

import '../data/api/kimai_repository.dart' as _i5;
import '../data/api/kimai_repository_impl.dart' as _i6;
import '../data/app_db.dart' as _i7;
import '../ui/common/bloc/authentication_bloc.dart' as _i8;
import '../ui/connect/cubit/connection_cubit.dart' as _i9;
import 'injectable_module.dart' as _i10;

// initializes the registration of main-scope dependencies inside of GetIt
Future<_i1.GetIt> $initGetIt(
  _i1.GetIt getIt, {
  String? environment,
  _i2.EnvironmentFilter? environmentFilter,
}) async {
  final gh = _i2.GetItHelper(
    getIt,
    environment,
    environmentFilter,
  );
  final injectableModule = _$InjectableModule();
  gh.factory<_i3.Dio>(() => injectableModule.dio);
  await gh.factoryAsync<_i4.Isar>(
    () => injectableModule.isar,
    preResolve: true,
  );
  gh.lazySingleton<_i5.KimaiRepository>(
      () => _i6.KimaiRepositoryImpl(dio: gh<_i3.Dio>()));
  gh.lazySingleton<_i7.AppDatabase>(
      () => _i7.AppDatabase(isar: gh<_i4.Isar>()));
  gh.lazySingleton<_i8.AuthenticationBloc>(
      () => _i8.AuthenticationBloc(db: gh<_i7.AppDatabase>()));
  gh.lazySingleton<_i9.ConnectionCubit>(() => _i9.ConnectionCubit(
        authenticationBloc: gh<_i8.AuthenticationBloc>(),
        repository: gh<_i5.KimaiRepository>(),
      ));
  return getIt;
}

class _$InjectableModule extends _i10.InjectableModule {}
