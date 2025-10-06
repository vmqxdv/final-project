import axios from 'axios';
import { useContext, useState, useEffect } from 'react';
import PizzaCard from './../components/PizzaCard';
// import GlobalContext from './../contexts/globalContext'

export default function HomePage() {

  const [pizzas, setPizzas] = useState([]);
  // const { setIsLoading } = useContext(GlobalContext);

  const fetchPizzas = () => {
    console.log('Caricando le pizze...');
    // setIsLoading(true);

    axios.get(import.meta.env.VITE_API_URL)
      .then(response => {
        console.log(response.data);

        const { data } = response;

        setPizzas(data);
      })
      .catch(err => {
        console.error(err)
      })
      .then(() => {
        // setIsLoading(false)
      })
  }

  useEffect(fetchPizzas, []);

  return (
    <>
      <h1 className="text-primary">Boopizzeria</h1>
      <section>
        <h2><i>Nuove pizze ogni giorno</i></h2>
        {
          pizzas.map((p) => <PizzaCard key={p.id} pizza={p} isDetail={true} />)
        }
      </section>
    </>
  )

}