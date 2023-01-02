import dayjs from 'dayjs/esm';

export interface IPerson {
  id: number;
  name?: string | null;
  surname?: string | null;
  email?: string | null;
  license?: string | null;
  start?: dayjs.Dayjs | null;
  end?: dayjs.Dayjs | null;
}

export type NewPerson = Omit<IPerson, 'id'> & { id: null };
