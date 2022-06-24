import { Link } from "react-router-dom";
import { Formik, Form, Field } from 'formik';
import * as Yup from "yup";
import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'

type SignUpData = {
  email: string,
  name: string,
  password: string,
  passwordConfirmation: string
};

const MySwal = withReactContent(Swal)

const validationSchema = Yup.object().shape({
  name: Yup.string()
    .min(2, 'Nome muito curto!')
    .max(50, 'Nome muito longo!')
    .required('Nome é obrigatório'),
  email: Yup.string()
    .email("Email inválido")
    .required("Email é obrigatório"),
  password: Yup.string()
    .required("Senha é obrigatória")
    .min(8, "A Senha precisa ter no mínimo 8 caracteres"),
  passwordConfirmation: Yup.string()
    .required("Senha é obrigatória")
    .oneOf([Yup.ref("password"), null], "As senhas não batem"),
});

const SignUp: React.FC = () => {
  const onSubmit = async (values:SignUpData) => {
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
    <div className="container flex h-screen mx-auto ">
      <div className="bg-gray-900 m-auto rounded-md w-full p-10">
        <h1 className="text-white text-3xl text-center">Registro</h1>
        <Formik
          initialValues={{
            name: '',
            email: '',
            password: '',
            passwordConfirmation: '',
          }}
          validationSchema={validationSchema}
          onSubmit={onSubmit}
        >
            {({ errors, touched }) => (
              <Form>
                <div className="mt-5">
                  <label htmlFor="name" className="text-white">
                    Nome
                  </label>
                  <Field
                    type="text"
                    id="name"               
                    name="name"
                    placeholder="Insira seu email"
                    className="bg-gray-700 border-gray-800 rounded-md p-2 text-white w-full"                 
                  />
                  {errors.name && touched.name ? (
                    <div className="text-red-600">{errors.name}</div>
                  ) : null}
                </div>
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
                <div className="mt-5">
                  <label htmlFor="password" className="text-white">
                    Confirmar Senha
                  </label>
                  <Field
                    type="password"
                    id="passwordConfirmation"
                    name="passwordConfirmation"
                    placeholder="Insira sua senha novamente"
                    className="bg-gray-700 border-gray-800 rounded-md p-2 text-white w-full"                   
                  />
                  {errors.passwordConfirmation && touched.passwordConfirmation ? (
                    <div className="text-red-600">{errors.passwordConfirmation}</div>
                  ) : null}
                </div>
                <div className="mt-5 text-white">
                  já possui uma conta ? clique{" "}
                  <Link className="text-blue-500" to="/sign-in">
                    aqui
                  </Link>{" "}
                  para logar
                </div>
                <button
                  type="submit"
                  className="p-2 mt-8 transition-all ease-in-out duration-200 bg-gray-700 hover:bg-gray-800 text-white font-bold rounded-md w-full"
                >
                  Criar Conta
                </button>
              </Form>
            )}
        </Formik>
      </div>
    </div>
  );
};

export default SignUp;
