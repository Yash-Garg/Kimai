import 'package:isar/isar.dart';

part '../../generated/data/schema/instance.g.dart';

@collection
class Instance {
  Id id = Isar.autoIncrement;

  late String baseURL;

  late String username;

  late String apiKey;

  @override
  String toString() {
    return 'Instance{baseURL: $baseURL, username: $username, apiKey: $apiKey}';
  }
}
