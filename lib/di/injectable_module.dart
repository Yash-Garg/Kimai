import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:isar/isar.dart';
import 'package:kimai/data/schema/instance.dart';
import 'package:path_provider/path_provider.dart';

@module
abstract class InjectableModule {
  Dio get dio => Dio();

  @preResolve
  Future<Isar> get isar async => Isar.open(
        [InstanceSchema],
        directory: (await getApplicationDocumentsDirectory()).path,
      );
}
