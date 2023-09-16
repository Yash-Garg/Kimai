import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:isar/isar.dart';
import 'package:kimai/data/app_db.dart';
import 'package:kimai/data/schema/instance.dart';
import 'package:kimai/di/injectable.dart';
import 'package:path_provider/path_provider.dart';

@module
abstract class InjectableModule {
  Dio get dio => Dio()
    ..interceptors.add(
      InterceptorsWrapper(
        onRequest: (options, handler) async {
          final db = getIt<AppDatabase>();
          final instance = await db.getInstance();

          if (instance != null) {
            options.baseUrl = instance.baseURL;
            options.headers['X-AUTH-USER'] = instance.username;
            options.headers['X-AUTH-TOKEN'] = instance.apiKey;
          }

          return handler.next(options);
        },
      ),
    );

  @preResolve
  Future<Isar> get isar async => Isar.open(
        [InstanceSchema],
        directory: (await getApplicationDocumentsDirectory()).path,
      );
}
