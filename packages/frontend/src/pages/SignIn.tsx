import { Link } from "react-router-dom";
import { Formik, Form, Field } from 'formik'
import * as Yup from "yup";
import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'

type SignInData = {
  email: string,
  password: string
}

const MySwal = withReactContent(Swal)

const validationSchema = Yup.object().shape({
  email: Yup.string()
    .email("Email inválido")
    .required("Email é obrigatório"),
  password: Yup.string()
    .required("Senha é obrigatória")
    .min(8, "A Senha precisa ter no mínimo 8 caracteres"),
});

const SignIn: React.FC = () => {
  const onSubmit = async (values:SignInData) => {
    try {
      await validationSchema.validate(values);
    } catch (error) {
      MySwal.fire(
        "Erro",
        (error as Error).message,
        "error"
      )
    }
  };

  return (
    <div className="container flex h-screen mx-auto">
      <div className="bg-gray-900 m-auto rounded-md w-full p-10">
        <h1 className="text-white text-3xl text-center">Login</h1>
        <Formik
          initialValues={{
            email: '',
            password: ''
          }}
          validationSchema={validationSchema}
          onSubmit={onSubmit}
        >
          {({ errors, touched }) => (
            <Form>
              <div className="mt-5">
                <label htmlFor="email" className="text-white">
                  Email
                </label>
                <Field
                  type="email"
                  id="email"
                  name="email"
                  placeholder="Insira seu email"
                  className="bg-gray-700 border-gray-800 rounded-md p-2 text-white w-full"
                />
                {errors.email && touched.email ? (
                  <div className="text-red-600">{errors.email}</div>
                ) : null}
              </div>
              <div className="mt-5">
                <label htmlFor="password" className="text-white">
                  Senha
                </label>
                <Field
                  type="password"
                  id="password"
                  name="password"
                  placeholder="Insira sua senha"
                  className="bg-gray-700 border-gray-800 rounded-md p-2 text-white w-full"
                />
                {errors.password && touched.password ? (
                  <div className="text-red-600">{errors.password}</div>
                ) : null}
              </div>
              <div className="mt-5 text-white">
                Ainda não possui uma conta? clique{" "}
                <Link className="text-blue-500" to="/sign-up">
                  aqui
                </Link>{" "}
                para criar a sua!
              </div>
              <button
                type="submit"
                className="p-2 mt-8 transition-all ease-in-out duration-200 bg-gray-700 hover:bg-gray-800 text-white font-bold rounded-md w-full"
              >
                Entrar
              </button>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
};

export default SignIn;
