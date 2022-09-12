import 'package:flutter/material.dart';
import 'package:vagas/home/widget/vaga.dart';
import 'package:vagas/model/userAuthModel.dart';
import 'package:vagas/model/vaga.dart';

class vagaList extends StatelessWidget {
  const vagaList({Key? key, required this.vaga, required this.usuario})
      : super(key: key);

  final Vaga vaga;
  final UserAuth usuario;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: (() => Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => vagaScreen(usuario: usuario),
            ),
          )),
      child: Padding(
        padding: const EdgeInsets.all(10.0),
        child: Container(
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(20),
          ),
          width: MediaQuery.of(context).size.width,
          height: MediaQuery.of(context).size.height * 0.15,
          child: Row(
            children: [
              Padding(
                padding: const EdgeInsets.all(12.0),
                child: AspectRatio(
                  aspectRatio: 1,
                  child: Container(
                    child: Icon(
                      Icons.restaurant_menu,
                      size: 80,
                      color: Color(0xffff6442),
                    ),
                    decoration: BoxDecoration(
                      color: Colors.grey.shade200,
                      borderRadius: BorderRadius.circular(10),
                    ),
                  ),
                ),
              ),
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(right: 12.0),
                    child: Text(
                      vaga.cargo,
                      style: TextStyle(
                        color: Colors.green,
                        fontWeight: FontWeight.bold,
                        fontSize: 20,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 5.0),
                    child: Text(
                      vaga.local,
                      style: TextStyle(
                        fontWeight: FontWeight.w200,
                        fontSize: 12,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 5.0),
                    child: Text(
                      vaga.dataPostada,
                      style: TextStyle(
                        fontWeight: FontWeight.w200,
                        fontSize: 11,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}