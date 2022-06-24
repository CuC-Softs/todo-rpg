import { Link } from "react-router-dom";
import { Formik, Form, Field } from 'formik';
import * as Yup from "yup";
import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'

const MySwal = withReactContent(Swal)

const validationSchema = Yup.object().shape({
  name: Yup.string().required("Nome é obrigatório"),
  email: Yup.string().email("Email inválido").required("Email é obrigatório"),
  password: Yup.string()
    .required("Senha é obrigatória")
    .required("Senha é obrigatória"),
  passwordConfirmation: Yup.string()
    .required("As senhas não batem")
    .oneOf([Yup.ref("password"), null], "As senhas não batem"),
});

const SignUp: React.FC = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirmation, setPasswordConfirmation] = useState("");

  const handleSubmit = async () => {
    const data = {
      name,
      email,
      password,
      passwordConfirmation,
    };
    try {
      await validationSchema.validate(data);
    } catch (error) {
      MySwal.fire(
        "Erro",
        (error as Error).message,
        "error"
      )
    }
  };

  return (
    <div className="container flex h-screen mx-auto ">
      <div className="bg-gray-900 m-auto rounded-md w-full p-10">
        <h1 className="text-white text-3xl text-center">Registro</h1>
        <form action="">
          <div className="mt-5">
            <label htmlFor="name" className="text-white">
              Nome
            </label>
            <input
              type="text"
              id="name"
              value={name}
              name="name"
              placeholder="Insira seu email"
              className="bg-gray-700 border-gray-800 rounded-md p-2 text-white w-full"
              onChange={e => setName(e.target.value)}
            />
          </div>
          <div className="mt-5">
            <label htmlFor="email" className="text-white">
              Email
            </label>
            <input
              value={email}
              type="email"
              id="email"
              name="email"
              onChange={event => {
                setEmail(event.target.value);
              }}
              placeholder="Insira seu email"
              className="bg-gray-700 border-gray-800 rounded-md p-2 text-white w-full"
            />
          </div>
          <div className="mt-5">
            <label htmlFor="password" className="text-white">
              Senha
            </label>
            <input
              type="password"
              id="password"
              name="password"
              value={password}
              placeholder="Insira sua senha"
              className="bg-gray-700 border-gray-800 rounded-md p-2 text-white w-full"
              onChange={event => {
                setPassword(event.target.value);
              }}
            />
          </div>
          <div className="mt-5">
            <label htmlFor="password" className="text-white">
              Confirmar Senha
            </label>
            <input
              type="password"
              id="passwordConfirmation"
              name="passwordConfirmation"
              placeholder="Insira sua senha novamente"
              className="bg-gray-700 border-gray-800 rounded-md p-2 text-white w-full"
              onChange={event => {
                setPasswordConfirmation(event.target.value);
              }}
            />
          </div>
          <div className="mt-5 text-white">
            já possui uma conta ? clique{" "}
            <Link className="text-blue-500" to="/sign-in">
              aqui
            </Link>{" "}
            para logar
          </div>
          <button
            type="button"
            onClick={handleSubmit}
            className="p-2 mt-8 transition-all ease-in-out duration-200 bg-gray-700 hover:bg-gray-800 text-white font-bold rounded-md w-full"
          >
            Criar Conta
          </button>
        </form>
      </div>
    </div>
  );
};

export default SignUp;
